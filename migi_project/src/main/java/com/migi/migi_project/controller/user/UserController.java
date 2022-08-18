package com.migi.migi_project.controller.user;

import com.migi.migi_project.entity.User;
import com.migi.migi_project.exception.BadRequestException;
import com.migi.migi_project.model.dto.UserDTO;
import com.migi.migi_project.model.mapper.UserMapper;
import com.migi.migi_project.model.request.LoginRequest;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ResponseEntity<?> checkLogin(@RequestBody LoginRequest request){

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
            //Gen token
            String token = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());

            //Add token to response
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMsg("Đăng nhập thành công");
            loginResponse.setUserDTO(UserMapper.toUserDTO(((CustomUserDetails) authentication.getPrincipal()).getUser()));
            loginResponse.setHttpStatus(HttpStatus.OK);
            loginResponse.setToken(token);

            return new ResponseEntity<LoginResponse>(loginResponse, loginResponse.getHttpStatus());
        } catch (Exception ex) {
            System.out.println("Tài khoản hoặc mật khẩu không chính xác!");
            return ResponseEntity.ok(new LoginResponse());
        }
    }

    @PutMapping(value = "/user")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getId());
        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
        System.out.println(userDTO.getFullname());
        System.out.println(userDTO.getPhoneNumber());
        System.out.println(userDTO.getAddress());
        System.out.println(userDTO.getCreateDate());

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

    @PostMapping(value = "/user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO, HttpServletResponse response){
        User user = userService.addUser(userDTO);
        if(user == null){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(UserMapper.toUserDTO(user));
    }
}
