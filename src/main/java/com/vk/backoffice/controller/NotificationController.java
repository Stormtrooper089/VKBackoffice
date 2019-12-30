package com.vk.backoffice.controller;

import com.vk.backoffice.qr.entity.NotificationRequest;
import com.vk.backoffice.qr.model.FcmNotification;
import com.vk.backoffice.qr.service.NotificationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationServiceImpl notificationService;

    @PostMapping("/public")
    public String pushNotificationToAllUser(@RequestBody NotificationRequest notification){
            return notificationService.pushNotificationToAllUser(notification);
    }

    @GetMapping("/list")
    public List<NotificationRequest> getAllNotifications(){
        return notificationService.getAllNotifications();
    }
}
