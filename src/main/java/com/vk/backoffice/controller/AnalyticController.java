package com.vk.backoffice.controller;

import com.vk.backoffice.qr.service.DashboardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytic")
public class AnalyticController {

    @Autowired
    DashboardServiceImpl dashboardService;

    @RequestMapping("/total/points/scanned/{fromDate}/{toDate}")
    public String getTotalPointsScanned(@PathVariable String fromDate,@PathVariable String toDate){
            return dashboardService.getTotalPointsScanned(fromDate,toDate);
    }

    @RequestMapping("/total/points/redeeemed/{fromDate}/{toDate}")
    public String getTotalPointsRedeemed(@PathVariable String fromDate,@PathVariable String toDate){
        return dashboardService.getTotalPointsScanned(fromDate,toDate);
    }
}
