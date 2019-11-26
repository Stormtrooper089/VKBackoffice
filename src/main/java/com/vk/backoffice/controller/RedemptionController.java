package com.vk.backoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.backoffice.qr.entity.RVRedemptionRequest;
import com.vk.backoffice.qr.repository.RedemptionRequestRepository;


@RestController
@RequestMapping("/api")
public class RedemptionController {
	
	@Autowired
	private RedemptionRequestRepository redemptionRequestRepository;
	
	@GetMapping("/redeemRequests")
    public List<RVRedemptionRequest> getTransactions(){
        return getRedeemRequestListFromDatabase();
    }

	private List<RVRedemptionRequest> getRedeemRequestListFromDatabase() {
		List<RVRedemptionRequest> redemptionlist = new ArrayList<RVRedemptionRequest>();
		redemptionlist = redemptionRequestRepository.findAll();
		return redemptionlist;
	}
}
