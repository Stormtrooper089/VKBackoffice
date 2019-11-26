package com.vk.backoffice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.backoffice.qr.entity.RVRedemptionRequest;
import com.vk.backoffice.qr.model.CreateQrRequest;
import com.vk.backoffice.qr.repository.RedemptionRequestRepository;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RedemptionController {
	
	@Autowired
	private RedemptionRequestRepository redemptionRequestRepository;
	
	@GetMapping("/redeemRequests")
    public List<RVRedemptionRequest> getTransactions(){
        return getRedeemRequestListFromDatabase();
    }
//	@PostMapping(path = "/updateRedemptionRequest")
//    public String updateRedemptionRequest(@RequestBody RVRedemptionRequest qrRequest) {
//        
//        return updateRedeemRequestStatus;
//    }

	private List<RVRedemptionRequest> getRedeemRequestListFromDatabase() {
		List<RVRedemptionRequest> redemptionlist = new ArrayList<RVRedemptionRequest>();
		redemptionlist = redemptionRequestRepository.findAll();
		List<RVRedemptionRequest> listAfterFilters = redemptionlist.stream()
				                                     .filter(e->e.getRedeemStatus().equalsIgnoreCase("Pending"))
				                                     .collect(Collectors.toList());
		return listAfterFilters;
	}
}
