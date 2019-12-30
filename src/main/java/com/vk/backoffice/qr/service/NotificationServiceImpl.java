package com.vk.backoffice.qr.service;

import com.vk.backoffice.qr.model.FcmNotification;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NotificationServiceImpl {

    @Value("${fcm.api}")
    String uri;
    @Value("${fcm.authorization.key}")
    String token;

    public String pushNotificationToAllUser(FcmNotification fcmNotification) {

        System.out.println("API"+ uri);
        System.out.println("Token "+ token);

        JSONObject msg = new JSONObject();
        msg.put("title", fcmNotification.getNotification().getTitle());
        msg.put("body", fcmNotification.getNotification().getBody());
        msg.put("notificationType", "Test");

        String response = callToFcmServer(msg, fcmNotification.getTo());
        return response;
    }

    private String callToFcmServer(JSONObject message, String receiverFcmKey) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "key=" + token);
        httpHeaders.set("Content-Type", "application/json");

        JSONObject json = new JSONObject();

        json.put("data", message);
        json.put("notification", message);
        json.put("to", receiverFcmKey);

        System.out.println("Sending :" + json.toString());

        HttpEntity<String> httpEntity = new HttpEntity<>(json.toString(), httpHeaders);
        return restTemplate.postForObject(uri, httpEntity, String.class);
    }
}
