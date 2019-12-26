package com.vk.backoffice.controller;

import com.vk.backoffice.qr.entity.User;
import com.vk.backoffice.qr.entity.UserRole;
import com.vk.backoffice.qr.repository.UserRepository;
import com.vk.backoffice.qr.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @GetMapping("/list")
    public List<User> getUserList(){
            return userRepository.findAll();
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
            if(user!= null){
                User newUser = new User();
                newUser.setDelFlg(user.getDelFlg());
                newUser.setEmailId(user.getEmailId());
                newUser.setMobileNumber(user.getMobileNumber());
                newUser.setPassword(user.getPassword());
                userRepository.save(newUser);
            }
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        if(user!= null){
            userRepository.save(user);
        }
    }

    @PostMapping("/assignRole")
    public void assignRole(@RequestBody List<UserRole> userRoleList){
        if(userRoleList.size() > 0) {
            List<UserRole> newRoleList = new ArrayList<>();
            for(UserRole userRole: userRoleList){
                UserRole role = new UserRole();
                role.setRole(userRole.getRole());
                role.setDelFlg(userRole.getDelFlg());
                role.setUserId(userRole.getUserId());
                newRoleList.add(role);
            }
            userRoleRepository.saveAll(newRoleList);
        }
    }

    @GetMapping("/roles/{userId}")
    public List<UserRole> getUserRole(@PathVariable(name = "userId") Long userId){
        if(userId != null) {
           return userRoleRepository.findAllById(Collections.singleton(userId));
        }
        else
        {
            return null;
        }
    }
}
