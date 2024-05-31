package com.manufacturing.manufacturingmanagementsystem.service.Users;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Role.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.mapper.RoleMapper;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Roles.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// Author: Pham Hien Nhan
// this class is used to implement the methods declared in the IUsersServices interface
@Service
public class UsersServices implements IUsersServices {
    private final UsersRepository usersRepository;
    private final RolesServices rolesServices;
    private final BCryptPasswordEncoder passwordEncoder;
    private RoleMapper roleMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    // Author: Pham Hien Nhan
    // this constructor is used to inject the UsersRepository, RolesServices, BCryptPasswordEncoder dependencies
    public UsersServices(UsersRepository usersRepository, RolesServices rolesServices, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesServices = rolesServices;
        this.passwordEncoder = passwordEncoder;
    }
    // Author: Pham Hien Nhan
    // this service is used to get all users
    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }
    // this service is used to get a user by id
    @Override
    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }
    // this service is used to insert a new user
    @Override
    public Map<String, Object> insertUser(UsersDTO userDto) {
        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            UsersEntity userEntity = new UsersEntity();
            if (usersRepository.findByEmail(userDto.getEmail()).isPresent()) {
                throw new RuntimeException("Email already exists");
            }
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userEntity.setFullName(userDto.getFullName());
            userEntity.setPhoneNumber(userDto.getPhoneNumber());
            userEntity.setAddress(userDto.getAddress());
            userEntity.setDateOfBirth(userDto.getDateOfBirth());
            System.out.println("Role find: " + rolesServices.getRoleByRoleName(userDto.getRoleName()));
            userEntity.setRole(rolesServices.getRoleByRoleName(userDto.getRoleName()));
            usersRepository.save(userEntity);

            if (userDto.getRoleName().isEmpty() || userDto.getRoleName().isBlank()){
                sendEmailSignUp(userDto.getEmail());
            }else{
                sendEmailAddUserByChairman(userDto.getEmail(), userDto.getRoleName());
            }


            Map<String, Object> userMap = new HashMap<>();
            System.out.println("Role map: " + userEntity.getRole());
            userMap.put("role", userEntity.getRole());
            userMap.put("email", userEntity.getEmail());
            userMap.put("fullName", userEntity.getFullName());
            userMap.put("phoneNumber", userEntity.getPhoneNumber());
            userMap.put("dateOfBirth", userEntity.getDateOfBirth());
            userMap.put("address", userEntity.getAddress());
            return userMap;
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert user: " + e.getMessage());
        }
    }
    // this service is used to update a user
    @Override
    public Map<String, Object> updateUser(long id, UsersDTO userDto) {
        try {
            Optional<UsersEntity> userEntityOptional  = usersRepository.findById(id);
            if (userEntityOptional.isPresent()) {
                UsersEntity userEntity = userEntityOptional.get();
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                if (userDto.getEmail() != null) {
                    userEntity.setEmail(userDto.getEmail());
                }
                if (userDto.getPassword() != null) {
                    userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
                }
                if (userDto.getFullName() != null) {
                    userEntity.setFullName(userDto.getFullName());
                }
                if (userDto.getPhoneNumber() != null) {
                    userEntity.setPhoneNumber(userDto.getPhoneNumber());
                }
                if (userDto.getAddress() != null) {
                    userEntity.setAddress(userDto.getAddress());
                }
                if (userDto.getDateOfBirth() != null) {
                    userEntity.setDateOfBirth(userDto.getDateOfBirth());
                }
                if (userDto.getRoleName() != null) {
                    userEntity.setRole(rolesServices.getRoleByRoleName(userDto.getRoleName()));
                };
                usersRepository.save(userEntity);
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("role", userEntity.getRole());
                userMap.put("email", userEntity.getEmail());
                userMap.put("fullName", userEntity.getFullName());
                userMap.put("phoneNumber", userEntity.getPhoneNumber());
                userMap.put("dateOfBirth", userEntity.getDateOfBirth());
                userMap.put("address", userEntity.getAddress());
                return userMap;
            } else {
                throw new RuntimeException("User not found with email : " + userDto.getEmail());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }
    // this service is used to reset a user's password
    @Override
    public UsersEntity resetPassword(long id, UsersDTO userDto) {
        try {
            Optional<UsersEntity> userEntityOptional = usersRepository.findById(id);
            if (userEntityOptional.isPresent()) {
                UsersEntity userEntity = userEntityOptional.get();
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                if (userDto.getPassword() != null) {
                    userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
                }
                usersRepository.save(userEntity);
                return userEntity;
            } else {
                throw new RuntimeException("User not found with id : " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }
    // this service is used to find a user by email
    @Override
    public UsersEntity findUserbyEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }
    // this service is used to delete a user
    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
    // this service is used to find a user by role
    @Override
    public UsersEntity findUserbyRole(String roleName) {
        return usersRepository.findByRole(roleName);
    }
    // this service is used to get the information of the current user
    public UserResponse getMyInfor() throws AppException {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        UsersEntity user = usersRepository.getInfoByEmail(email);
        RolesEntity userRole = rolesServices.getRoleByRoleName(user.getRole().getRoleName());
        RoleResponse role = roleMapper.toRoleResponse(userRole);
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        UserResponse userResponse = new UserResponse();

        userResponse.setRole(role);
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setFullName(user.getFullName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        userResponse.setAddress(user.getAddress());
        return userResponse;
    }
    // this service is used to get the information of the current user
    @Override
    public List<UsersEntity> findAllSignUpRequest(long id) {
        try {
            Optional<UsersEntity> userEntityOptional = usersRepository.findById(id);
            if (userEntityOptional.isPresent()){
                return usersRepository.findNullRoleId();
            }
            else {
                throw new RuntimeException("Only chairman can accept signup request");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to accept signup request: " + e.getMessage());
        }
    }
    // this service is used to update the role of a user
    @Override
    public UsersEntity updateRoleId(String email, UsersDTO usersDTO) {
        try {
            Optional<UsersEntity> userEntityOptional = usersRepository.findByEmail(email);
            System.out.println(email);
            if (userEntityOptional.isPresent()){
                UsersEntity userEntity = userEntityOptional.get();
                userEntity.setRole(rolesServices.getRoleByRoleName(usersDTO.getRoleName()));
                System.out.println(email);
                System.out.println(rolesServices.getRoleByRoleName(usersDTO.getRoleName()));

                sendEmailSignUpAccept(email, usersDTO.getRoleName());
                usersRepository.save(userEntity);
                return userEntity;
            }
            else {
                throw new RuntimeException("No signup request has email: " + email);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update role for signup: " + e.getMessage());
        }
    }
    // this service is used to get the information of the current user
    @Override
    public List<UsersEntity> findAllEmployee(long id) {
        try {
            Optional<UsersEntity> userEntityOptional = usersRepository.findById(id);
            if (userEntityOptional.isPresent()){
                return usersRepository.findNotNullRoleId();
            }
            else {
                throw new RuntimeException("Only chairman can view list of employee");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to view list of employee: " + e.getMessage());
        }
    }
    // this service is used to get the information of the current user
    private void sendEmailAddUserByChairman(String to, String roleName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to Manufactorio");
            message.setText("Thanks for choosing our company. This would be our pleasure to work with you." + "\n\n" +
                    "Your responsibility or working role is: " + roleName + "\n\n" +
                    "Your default password is: 123456. Please login and update it later in your personal profile." + "\n\n" +
                    "If you have any questions about our production or services, please feel free to contact us at any time.\n\n" +
                    "Sincerely,\nManufactorio");
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
    // this service is used to get the information of the current user
    private void sendEmailSignUp(String to) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to Manufactorio");
            message.setText("Thanks for choosing our company. This would be our pleasure to work with you." + "\n\n" +
                    "To access Manufactorio mobile app, please wait for the administrator to accept your signup request." + "\n\n" +
                    "If you have any questions about our production or services, please feel free to contact us at any time.\n\n" +
                    "Sincerely,\nManufactorio");
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
    // this service is used to get the information of the current user
    private void sendEmailSignUpAccept(String to, String roleName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to Manufactorio");
            message.setText("Your signup request has been accepted. Hope we will spend long-time together and gain more successes." + "\n\n" +
                    "Your responsibility or working role is: " + roleName + "\n\n" +
                    "If you have any questions about our production or services, please feel free to contact us at any time.\n\n" +
                    "Sincerely,\nManufactorio");
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}

