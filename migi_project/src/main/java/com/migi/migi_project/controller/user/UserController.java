package com.migi.migi_project.controller.user;

import com.migi.migi_project.entity.User;
import com.migi.migi_project.model.dto.UserDTO;
import com.migi.migi_project.model.mapper.UserMapper;
import com.migi.migi_project.model.request.LoginRequest;
import com.migi.migi_project.model.request.UpdatePasswordRequest;
import com.migi.migi_project.model.response.LoginResponse;
import com.migi.migi_project.security.CustomUserDetails;
import com.migi.migi_project.security.JwtTokenUtil;
import com.migi.migi_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public ResponseEntity<?> checkLogin(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
            //Gen token
            String token = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());

            //Add token to response
            loginResponse.setMsg("Đăng nhập thành công");
            loginResponse.setUserDTO(UserMapper.toUserDTO(((CustomUserDetails) authentication.getPrincipal()).getUser()));
            loginResponse.setHttpStatus(HttpStatus.OK);
            loginResponse.setToken(token);

        } catch (Exception ex) {
            loginResponse.setMsg("Tài khoản hoặc mật khẩu không chính xác!");
            loginResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            System.out.println("Tài khoản hoặc mật khẩu không chính xác!");
        }
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping(value = "/user/get-by-email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PutMapping(value = "/user")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO reDTO = userService.updateUser(userDTO);
        String token = jwtTokenUtil.generateToken(
                (CustomUserDetails)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal());
        //Add token to response
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMsg("Cập nhật thành công");
        loginResponse.setUserDTO(reDTO);
        loginResponse.setHttpStatus(HttpStatus.OK);
        loginResponse.setToken(token);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(value = "/user/update-password/{id}")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request,
                                            @PathVariable("id") Integer id) {

        return ResponseEntity.ok(userService.updatePassword(id, request));
    }

    @PutMapping(value = "/user/mobile")
    public ResponseEntity<?> updateUserMobile(@RequestBody UserDTO userDTO) {
        UserDTO reDTO = userService.updateUser(userDTO);
        //Add token to response
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMsg("Cập nhật thành công");
        loginResponse.setUserDTO(reDTO);
        loginResponse.setHttpStatus(HttpStatus.OK);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        User user = userService.addUser(userDTO);
        if (user == null) {
            return ResponseEntity.badRequest().body("Username or email existed");
        }

        return ResponseEntity.ok(UserMapper.toUserDTO(user));
    }

    @PostMapping("/user/{id}/upload_photo")
    public ResponseEntity<?> uploadAvatar(@PathVariable("id") int id, @RequestPart MultipartFile file) {
        return ResponseEntity.ok(userService.uploadAvatar(id, file));
    }
}
