package com.migi.migi_project.service.user;

import com.migi.migi_project.entity.User;
import com.migi.migi_project.model.dto.UserDTO;
import com.migi.migi_project.model.request.UpdatePasswordRequest;
import com.migi.migi_project.model.response.ResponseUploadFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
    UserDTO checkLogin(String username, String password);
    User findUserById(Integer id);
    UserDTO updateUser(UserDTO userDTO);
    User addUser(UserDTO userDTO);

    ResponseUploadFile<String> uploadAvatar(int id, MultipartFile file);

    ResponseUploadFile<UserDTO> findByEmail(String email);

    ResponseUploadFile<UserDTO> updatePassword(Integer id, UpdatePasswordRequest request);
}
