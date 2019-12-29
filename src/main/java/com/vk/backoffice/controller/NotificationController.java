package com.vk.backoffice.controller;

import com.vk.backoffice.qr.model.FcmNotification;
import com.vk.backoffice.qr.service.NotificationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationServiceImpl notificationService;

    @PostMapping("/public")
    public String pushNotificationToAllUser(@RequestBody FcmNotification notification){
            return notificationService.pushNotificationToAllUser(notification);
    }
}
