package com.manufacturing.manufacturingmanagementsystem.service.Users;

import com.manufacturing.manufacturingmanagementsystem.dtos.RolesDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Roles.RolesServices;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServices implements IUsersServices {

    private final UsersRepository usersRepository;
    private final RolesServices rolesServices;

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public UsersEntity insertUser(UsersDTO userDto) {
        UsersEntity userEntity = new UsersEntity();
        // Set the properties of the userEntity based on the userDto
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRole(rolesServices.getRoleById(userDto.getRoleId()));
        return usersRepository.save(userEntity);

    }

    @Override
    public UsersEntity updateUser(UsersDTO userDto) {
        UsersEntity userEntity = usersRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (userEntity != null) {
            // Update the properties of the userEntity based on the userDto
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPassword(userDto.getPassword());
            userEntity.setRole(rolesServices.getRoleById(userDto.getRoleId()));

            return usersRepository.save(userEntity);
        } else {
            // Handle the case where no user with the given ID exists
            throw new RuntimeException("User not found with id : " + userDto.getFullName());
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
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setUserid(user.getId());
        userResponse.setRoleId(user.getRole().getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setFullName(user.getFullName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        userResponse.setAddress(user.getAddress());
        return userResponse;
    }


}

