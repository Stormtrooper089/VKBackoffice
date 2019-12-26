package com.vk.backoffice.controller;

import com.vk.backoffice.qr.entity.User;
import com.vk.backoffice.qr.entity.UserRole;
import com.vk.backoffice.qr.repository.UserRepository;
import com.vk.backoffice.qr.repository.UserRoleRepository;
import com.vk.backoffice.qr.util.RequestStatusResponse;
import com.vk.backoffice.qr.util.VankonConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/list")
    public List<User> getUserList(){
            return userRepository.findAll();
    }

    @PostMapping("/createUser")
    public RequestStatusResponse createUser(@RequestBody User user){
            if(user!= null){
                RequestStatusResponse response = new RequestStatusResponse();
                try {
                    User newUser = new User();
                    newUser.setDelFlg(user.getDelFlg());
                    newUser.setEmailId(user.getEmailId());
                    newUser.setMobileNumber(user.getMobileNumber());
                    newUser.setPassword(user.getPassword());
                    userRepository.save(newUser);
                    response.setResponseStatus(VankonConstant.SUCCESS);
                    response.setResponseStatusDescription("User Created Successfully . .");
                }catch (Exception e){
                    response.setResponseStatus(VankonConstant.FAILURE);
                    response.setResponseStatusDescription("User Creation Failed .. Try Again");
                    logger.error(">>> CREATE USER "+e);
                }finally {
                    return response;
                }
            }
            return null;
    }

    @PostMapping("/updateUser")
    public RequestStatusResponse updateUser(@RequestBody User user){
        if(user!= null){
            RequestStatusResponse response = new RequestStatusResponse();
            try {
                userRepository.save(user);
                response.setResponseStatus(VankonConstant.SUCCESS);
                response.setResponseStatusDescription("User Updated Successfully . .");

            }catch (Exception e){
                response.setResponseStatus(VankonConstant.FAILURE);
                response.setResponseStatusDescription("User Creation Failed .. Try Again");
                logger.error(">>> UPDATE USER "+e);
            }finally {
                return response;
            }
        }
        return null;
    }

    @PostMapping("/assignRole")
    public RequestStatusResponse assignRole(@RequestBody List<UserRole> userRoleList){
        if(userRoleList.size() > 0) {
            List<UserRole> newRoleList = new ArrayList<>();
            for(UserRole userRole: userRoleList){
                UserRole role = new UserRole();
                role.setRole(userRole.getRole());
                role.setDelFlg(userRole.getDelFlg());
                role.setUserId(userRole.getUserId());
                newRoleList.add(role);
            }
            RequestStatusResponse response = new RequestStatusResponse();
            try {
            userRoleRepository.saveAll(newRoleList);
                response.setResponseStatus(VankonConstant.SUCCESS);
                response.setResponseStatusDescription("Roles assigned Successfully . .");

            }catch (Exception e){
                response.setResponseStatus(VankonConstant.FAILURE);
                response.setResponseStatusDescription("Roles assignment Failed .. Try Again");
                logger.error(">>> ROLE ASSIGNMENT "+e);
            }finally {
                return response;
            }
        }
        return null;
    }

    @GetMapping("/roles/{userId}")
    public List<UserRole> getUserRole(@PathVariable(name = "userId") Long userId){
        if(userId != null) {
           return userRoleRepository.getRolesByUserId(userId);
        }
        else
        {
            return null;
        }
    }
}
