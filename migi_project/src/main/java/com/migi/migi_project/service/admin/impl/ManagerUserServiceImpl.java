package com.migi.migi_project.service.admin.impl;

import com.migi.migi_project.entity.Orders;
import com.migi.migi_project.entity.Role;
import com.migi.migi_project.entity.User;
import com.migi.migi_project.entity.UserRole;
import com.migi.migi_project.exception.BadRequestException;
import com.migi.migi_project.exception.NotAllowRequestException;
import com.migi.migi_project.model.dto.UserDTO;
import com.migi.migi_project.model.mapper.UserMapper;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.repository.user.OrderRepository;
import com.migi.migi_project.repository.user.RoleRepository;
import com.migi.migi_project.repository.user.UserRepository;
import com.migi.migi_project.repository.user.UserRoleRepository;
import com.migi.migi_project.service.admin.ManagerUserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ManagerUserServiceImpl implements ManagerUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserDTO> findAllUsers(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        List<User> users = userRepository.findAll(pageable).getContent();
        List<UserDTO> result = new ArrayList<>();
        for(User x : users){
            result.add(UserMapper.toUserDTO(x));
        }
        return result;
    }

    @Override
    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public ResponseNormal addUser(UserDTO userDTO) {
        if(checkDuplicateUsername(userDTO)){
            return new ResponseNormal(
                    "Username: "+userDTO.getUsername()+" đã tồn tại, thêm mới không thành công!",
                    HttpStatus.BAD_REQUEST);
        }

        // Hash password using BCrypt
        String hash = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12));
        User user = UserMapper.toUser(userDTO);
        user.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        user.setPassword(hash);
        //Lưu
        User newUser = userRepository.save(user);
        //Lưu các role_user của new user
        for(String s: userDTO.getRoles()){
            Role role = roleRepository.findByName(s).get();
            UserRole userRole = new UserRole();
            userRole.setUserByIdUser(newUser);
            userRole.setRoleByIdRole(role);
            userRoleRepository.save(userRole);
        }
        return new ResponseNormal("Thêm mới thành công", HttpStatus.OK);
    }

    @Override
    public ResponseNormal deleteUser(Integer id) {
        //Người dùng có đã từng order thì không được xóa
        if(checkUserHasOrder(id)){
           return new ResponseNormal("Người dùng đã có đơn đặt hàng, không thể xóa!",HttpStatus.BAD_REQUEST);
        }
        List<UserRole> userRoleList = userRoleRepository.findByIdUser(id);
        if(userRoleList.size() > 0){
            for(UserRole x : userRoleList){
                userRoleRepository.delete(x);
            }
        }
        userRepository.deleteById(id);
        return new ResponseNormal("Xóa thành công",HttpStatus.OK);
    }

    @Override
    public ResponseNormal updateUser(UserDTO userDTO) {
        if(checkDuplicateUsername(userDTO)){
            return new ResponseNormal(
                    "Username: "+userDTO.getUsername()+" đã tồn tại, sửa không thành công!",
                    HttpStatus.BAD_REQUEST);
        }
        //Lấy list role cũ và mới sắp xếp và so sánh sự thay đổi
        boolean changed = false;
        Collection<String> oldRoles= UserMapper.toUserDTO(userRepository.findById(userDTO.getId()).get()).getRoles();
        List<String> oldListRole = oldRoles.stream().collect(Collectors.toList());
        List<String> newListRole = userDTO.getRoles().stream().collect(Collectors.toList());
        if(oldListRole.size() == newListRole.size()){
            Collections.sort(oldListRole);
            Collections.sort(newListRole);
            for(int i=0; i<oldListRole.size(); i++){
                if(!oldListRole.get(i).equals(newListRole.get(i))){
                    changed = true;
                }
            }
        }else {
            changed = true;
        }

        //Lưu thông tin userDTO gửi lên
        User user = userRepository.save(UserMapper.toUser(userDTO));

        //Nếu có thay đổi role thì sửa lại
        if(changed){
            //Xóa hết user role của user rồi gán lại
            List<UserRole> userRoleList= userRoleRepository.findByIdUser(userDTO.getId());
            for(UserRole x : userRoleList){
                userRoleRepository.delete(x);
            }
            if(userDTO.getRoles().size()>0){
                for(String s : userDTO.getRoles()){
                    UserRole userRole = new UserRole();
                    userRole.setRoleByIdRole(roleRepository.findByName(s).get());
                    userRole.setUserByIdUser(user);
                    userRoleRepository.save(userRole);
                }
            }
        }
        return new ResponseNormal("Cập nhật thành công!", HttpStatus.OK);
    }

    //Check người dùng đã có order nào chưa thanh toán không?
    public boolean checkUserHasOrder(Integer id){
        List<Orders> orders = orderRepository.findAllByIdUser(id);
        if(orders.size() > 0){
            return true;
        }
        return false;
    }

    //Check trùng username trước khi sửa
    public boolean checkDuplicateUsername(UserDTO userDTO){
        List<User> list = userRepository.findAll();
        for(User u : list){
            if(u.getUsername().equals(userDTO.getUsername())
                    &&
                    !u.getId().equals(userDTO.getId()) ){
                return true;
            }
        }
        return false;
    }
}
