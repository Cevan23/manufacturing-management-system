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

@Service
//@AllArgsConstructor
public class UsersServices implements IUsersServices {

    private final UsersRepository usersRepository;
    private final RolesServices rolesServices;
    private final BCryptPasswordEncoder passwordEncoder;
    private RoleMapper roleMapper;

    public UsersServices(UsersRepository usersRepository, RolesServices rolesServices, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesServices = rolesServices;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

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
            userEntity.setStatus(1);
            userEntity.setCreatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            userEntity.setModifiedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            userEntity.setCreatedBy("admin@gmail.com");
            userEntity.setModifiedBy("admin@gmail.com");
            usersRepository.save(userEntity);
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
            // Xử lý lỗi và ném ra ngoại lệ mới hoặc trả về null (tùy thuộc vào yêu cầu)
            throw new RuntimeException("Failed to insert user: " + e.getMessage());
        }
    }


    @Override
    public Map<String, Object> updateUser(long id,UsersDTO userDto) {
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
                userEntity.setStatus(1);
                userEntity.setModifiedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
                userEntity.setModifiedBy("admin@gmail.com");
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
                // Handle the case where no user with the given ID exists
                throw new RuntimeException("User not found with email : " + userDto.getEmail());
            }
        } catch (Exception e) {
            // Xử lý lỗi và ném ra ngoại lệ mới hoặc trả về null (tùy thuộc vào yêu cầu)
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }

    @Override
    public UsersEntity findUserbyEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public UsersEntity findUserbyRole(String roleName) {
        return usersRepository.findByRole(roleName);
    }

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


}

