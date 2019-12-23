package com.vk.backoffice.controller;

import com.vk.backoffice.aspect.TrackTime;
import com.vk.backoffice.qr.entity.RVRedemptionRequest;
import com.vk.backoffice.qr.repository.RedemptionRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class RedemptionController {

    @Autowired
    private RedemptionRequestRepository redemptionRequestRepository;

    Logger log = LoggerFactory.getLogger(RedemptionController.class);

    @TrackTime
    @GetMapping("/redeemRequests")
    public List<RVRedemptionRequest> getTransactions() {
        return getRedeemRequestListFromDatabase();
    }

    private List<RVRedemptionRequest> getRedeemRequestListFromDatabase() {
        List<RVRedemptionRequest> redemptionlist = new ArrayList<RVRedemptionRequest>();
        redemptionlist = redemptionRequestRepository.findAll();
        log.info("redemptionList - "+redemptionlist.size());
        List<RVRedemptionRequest> listAfterFilters = redemptionlist.stream()
                .filter(e -> e.getRedeemStatus().equalsIgnoreCase("Pending"))
                .collect(Collectors.toList());

        log.info("redemptionList AFTER FILTER- "+listAfterFilters.size());
        return listAfterFilters;
    }


    @TrackTime
    @PostMapping(path = "/postRedeemRequests")
    public List<RVRedemptionRequest> updateRedemptionRequest(@RequestBody RVRedemptionRequest qrRequest) {
        try {
            log.info(qrRequest.toString());
            RVRedemptionRequest redemptionRequest = redemptionRequestRepository.findById(qrRequest.getId()).orElse(new RVRedemptionRequest());
            redemptionRequest.setRedeemStatus(qrRequest.getRedeemStatus());
            Date currentDate= new Date();
            redemptionRequest.setRedeemDate(currentDate.toString());
            redemptionRequestRepository.save(redemptionRequest);
            return getRedeemRequestListFromDatabase();
        }
        catch (Exception e){
            return null;
        }
    }
}
