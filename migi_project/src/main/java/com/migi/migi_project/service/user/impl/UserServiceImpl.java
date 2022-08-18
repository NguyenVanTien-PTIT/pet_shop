package com.migi.migi_project.service.user.impl;

import com.migi.migi_project.entity.Role;
import com.migi.migi_project.entity.User;
import com.migi.migi_project.entity.UserRole;
import com.migi.migi_project.model.dto.UserDTO;
import com.migi.migi_project.model.mapper.UserMapper;
import com.migi.migi_project.repository.user.RoleRepository;
import com.migi.migi_project.repository.user.UserRepository;
import com.migi.migi_project.repository.user.UserRoleRepository;
import com.migi.migi_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDTO checkLogin(String username, String password) {
        if(userRepository.checkUser(username, password).isPresent()){
            User user = userRepository.checkUser(username, password).get();
            return UserMapper.toUserDTO(user);
        }
        return null;
    }

    @Override
    public User findUserById(Integer id) {
        return (userRepository.findById(id).get());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User  user = userRepository.findById(userDTO.getId()).get();
        user.setAddress(userDTO.getAddress());
        user.setFullname(userDTO.getFullname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return UserMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public User addUser(UserDTO userDTO) {
        //Check username
        if(userRepository.findUserByUsername(userDTO.getUsername()).isPresent()){
            return null;
        }
        //Set ngày tạo
        userDTO.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12));
        userDTO.setPassword(hash);
        //Lưu lại user
        User user = userRepository.save(UserMapper.toUser(userDTO));
        UserRole userRole= new UserRole();
        userRole.setUserByIdUser(user);
        userRole.setRoleByIdRole(roleRepository.findById(2).get());
        userRoleRepository.save(userRole);
        Collection<UserRole> listRole = new ArrayList<>();
        listRole.add(userRole);
        user.setUserRolesById(listRole);
        return user;
    }
}
