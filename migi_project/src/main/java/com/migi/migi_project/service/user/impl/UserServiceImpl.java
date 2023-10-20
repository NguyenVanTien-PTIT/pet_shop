package com.migi.migi_project.service.user.impl;

import com.migi.migi_project.entity.User;
import com.migi.migi_project.entity.UserRole;
import com.migi.migi_project.model.dto.UserDTO;
import com.migi.migi_project.model.mapper.UserMapper;
import com.migi.migi_project.model.request.UpdatePasswordRequest;
import com.migi.migi_project.model.response.ResponseUploadFile;
import com.migi.migi_project.repository.user.RoleRepository;
import com.migi.migi_project.repository.user.UserRepository;
import com.migi.migi_project.repository.user.UserRoleRepository;
import com.migi.migi_project.security.CustomUserDetails;
import com.migi.migi_project.service.user.UserService;
import com.migi.migi_project.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final static String UPLOAD_DIR = FileUtils.getResourceBasePath() + "\\src\\main\\resources\\images";

    @Override
    public UserDTO checkLogin(String username, String password) {
        if (userRepository.checkUser(username, password).isPresent()) {
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
        User user = userRepository.findById(userDTO.getId()).get();
        user.setAddress(userDTO.getAddress());
        user.setFullname(userDTO.getFullname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        return UserMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public User addUser(UserDTO userDTO) {
        //Check username
        if (userRepository.findUserByUsername(userDTO.getUsername()).isPresent()) {
            return null;
        }

        // Check email
        if (userRepository.findByEmail(userDTO.getUsername()).isPresent()) {
            return null;
        }

        // check first and last name
        if (Strings.isBlank(userDTO.getFirstName()) || Strings.isBlank(userDTO.getLastName())) {
            String[] names = userDTO.getFullname().split(" ");

            AtomicInteger index = new AtomicInteger(1);

            Arrays.stream(names).forEach((name) -> {
                if (index.get() < names.length) {
                    userDTO.setFirstName(
                            (Strings.isBlank(userDTO.getFirstName())
                                    ? ""
                                    : (userDTO.getFirstName() + " "))
                                    + name
                    );
                } else {
                    userDTO.setLastName(name);
                }
                index.getAndIncrement();
            });
        }

        //Set ngày tạo
        userDTO.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12));
        userDTO.setPassword(hash);
        //Lưu lại user
        User user = userRepository.save(UserMapper.toUser(userDTO));
        UserRole userRole = new UserRole();
        userRole.setUserByIdUser(user);
        userRole.setRoleByIdRole(roleRepository.findById(2).get());
        userRoleRepository.save(userRole);
        Collection<UserRole> listRole = new ArrayList<>();
        listRole.add(userRole);
        user.setUserRolesById(listRole);
        return user;
    }

    @Override
    public ResponseUploadFile<String> uploadAvatar(int id, MultipartFile multipartFile) {
        String avatarName = "";

        //Tạo thư mục chứa ảnh nếu không tồn tại
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if (Objects.isNull(multipartFile)) {
            return new ResponseUploadFile<>("File không hợp lệ!", HttpStatus.BAD_REQUEST, "");
        }

        //Lấy tên file và đuôi mở rộng của file
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (originalFilename.length() > 0) {

            //Kiểm tra xem file có đúng định dạng không
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                return new ResponseUploadFile<>("Không hỗ trợ định dạng file này!", HttpStatus.BAD_REQUEST, "");
            }
            try {
                avatarName = UUID.randomUUID().toString() + "." + extension;
                String linkFile = UPLOAD_DIR + "\\" + avatarName;
                //Tạo file
                File file = new File(linkFile);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(multipartFile.getBytes());
                bos.close();
            } catch (Exception e) {
                System.out.println("Có lỗi trong quá trình upload file!");
            }
        }

        User user = userRepository.findById(id).get();
        user.setImage(avatarName);
        userRepository.save(user);

        return new ResponseUploadFile<>("Success", HttpStatus.OK, avatarName);
    }

    @Override
    public ResponseUploadFile<UserDTO> findByEmail(String email) {
        ResponseUploadFile.ResponseUploadFileBuilder<UserDTO> builder =
                ResponseUploadFile
                        .<UserDTO>builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .msg("Not found customer by email");

        userRepository.findByEmail(email).ifPresent(user -> {
            builder.httpStatus(HttpStatus.OK)
                    .data(UserMapper.toUserDTO(user))
                    .msg("Success");
        });

        return builder.build();
    }

    @Override
    public ResponseUploadFile<UserDTO> updatePassword(Integer id, UpdatePasswordRequest request) {
        ResponseUploadFile.ResponseUploadFileBuilder<UserDTO> builder =
                ResponseUploadFile
                        .<UserDTO>builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .msg("System error");

        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return builder.build();
        }
        User user = optionalUser.get();

        // update password
        if (StringUtils.isNotBlank(request.getOldPassword())) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        request.getOldPassword()
                ));
            } catch (Exception ex) {
                ex.printStackTrace();
                return builder.msg("Mật khẩu hiện tại không chính xác!")
                        .build();
            }

        }

        // Hash password using BCrypt
        String hash = BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        userRepository.save(user);

        return builder
                .httpStatus(HttpStatus.OK)
                .msg("Cập nhật mật khẩu thành công")
                .data(UserMapper.toUserDTO(user))
                .build();
    }
}
