package com.vk.backoffice.qr.service;

import com.vk.backoffice.qr.model.FcmNotification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl {

    @Value("${fcm.api}")
    String uri;
    @Value("${fcm.authorization.key}")
    String token;

    public String pushNotificationToAllUser(FcmNotification fcmNotification) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate restTemplate =  new RestTemplate();
        return restTemplate.postForObject(uri,fcmNotification,String.class,entity);
    }
}
