package com.migi.migi_project.model.mapper;

import com.migi.migi_project.entity.Role;
import com.migi.migi_project.entity.User;
import com.migi.migi_project.entity.UserRole;
import com.migi.migi_project.model.dto.UserDTO;
import com.migi.migi_project.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMapper {
    @Autowired
    private RoleRepository roleRepository;

    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFullname(user.getFullname());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setCreateDate(user.getCreateDate());
        Collection<String> roles = new ArrayList<>();
        for(UserRole x : user.getUserRolesById()){
            roles.add(x.getRoleByIdRole().getName());
        }
        userDTO.setRoles(roles);
        return userDTO;
    }

    public static User toUser(UserDTO userDTO){
        User user= new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFullname(userDTO.getFullname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setCreateDate(userDTO.getCreateDate());
        return user;
    }
}
