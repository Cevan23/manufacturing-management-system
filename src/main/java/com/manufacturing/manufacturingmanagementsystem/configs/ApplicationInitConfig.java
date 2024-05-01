package com.manufacturing.manufacturingmanagementsystem.configs;


import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.RolesRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;

@Configuration
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private RolesRepository roleRepository;

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    public ApplicationRunner applicationRunner() {
        return args -> {
            initRoles();
            initAdminUser();
        };
    }

    private void initRoles() {
        if (roleRepository.findRoleByRoleName("chairman") != null) {
            RolesEntity chairmanRole = new RolesEntity();
            chairmanRole.setRoleName("chairman");
            roleRepository.save(chairmanRole);
            System.out.println("Role chairman has been created");
        }
    }

    private void initAdminUser() {
        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            UsersEntity adminUser = new UsersEntity();
            adminUser.setEmail("admin@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("123456"));
            // Gán vai trò "chairman" cho người dùng "admin"
            RolesEntity chairmanRole = roleRepository.findRoleByRoleName("chairman");
            if (chairmanRole != null) {
                adminUser.setFullName("Admin");
                adminUser.setPhoneNumber("1234567890");
                adminUser.setAddress("123 Street, City");
                adminUser.setDateOfBirth(new Date());
                adminUser.setStatus(1);
                adminUser.setCreatedDate(new Date());
                adminUser.setModifiedDate(new Date());
                adminUser.setCreatedBy("admin");
                adminUser.setModifiedBy("admin");
                userRepository.save(adminUser);
            }

            System.out.println("Admin user has been created with default password: 123456");
        }
    }
}

