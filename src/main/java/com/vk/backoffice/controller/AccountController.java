package com.vk.backoffice.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.vk.backoffice.aspect.TrackTime;
import com.vk.backoffice.qr.entity.RVRedemptionRequest;
import com.vk.backoffice.qr.entity.RVUser;
import com.vk.backoffice.qr.entity.RVUserKyc;
import com.vk.backoffice.qr.service.UserServiceImpl;
import com.vk.backoffice.qr.util.RequestStatusResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	 Logger log = LoggerFactory.getLogger(AccountController.class);

	    @TrackTime
	    @GetMapping("/account/list")
	    public List<RVUser> getTransactions() {
	        return userServiceImpl.getAllUsers();
	    }
	    
	    @TrackTime
	    @PostMapping(path = "/updateUser")
	    public RequestStatusResponse updateUser(@RequestBody RVUser toUpdateUser) {
	        return userServiceImpl.updateUser(toUpdateUser);
	    }
	    
	    @TrackTime
	    @GetMapping("/account/showKyc/{mobileNumber}")
	    public Optional<RVUserKyc> showKyc(@PathVariable String mobileNumber) {
	        return userServiceImpl.getUserKyc(mobileNumber);
	    }
	    
	    @TrackTime
	    @GetMapping("/account/kyc/{mobileNumber}/{status}")
	    public RequestStatusResponse updateKycStatus(@PathVariable String mobileNumber,@PathVariable String status) {
	        return userServiceImpl.updateKycStatus(mobileNumber,status);
	    }
	    
}
