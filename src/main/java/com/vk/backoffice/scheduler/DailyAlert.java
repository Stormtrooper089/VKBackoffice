package com.vk.backoffice.scheduler;

import com.vk.backoffice.qr.service.DashboardServiceImpl;
import com.vk.backoffice.qr.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DailyAlert {

    @Autowired
    DashboardServiceImpl dashboardService;
    @Autowired
    EmailUtil emailUtil;
    @Scheduled(cron = "0 0 20 ? * *" , zone = "Asia/Colombo")
        public void run(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String fromDate = simpleDateFormat.format(currentDate);

        String totalPointsScanned = dashboardService.getTotalPointsScanned(fromDate,fromDate);
        String totalPointsRedeemed = dashboardService.getTotalPointsRedeemed(fromDate,fromDate);
        List<Object[]> productList = dashboardService.getTop10ScannedProducts(fromDate,fromDate);
        emailUtil.sendEmailWithAttachment(totalPointsScanned,totalPointsRedeemed,productList);
        }
}
