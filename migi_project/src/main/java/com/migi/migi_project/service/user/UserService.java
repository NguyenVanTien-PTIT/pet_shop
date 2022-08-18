package com.migi.migi_project.service.user;

import com.migi.migi_project.entity.User;
import com.migi.migi_project.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO checkLogin(String username, String password);
    User findUserById(Integer id);
    UserDTO updateUser(UserDTO userDTO);
    User addUser(UserDTO userDTO);

}
