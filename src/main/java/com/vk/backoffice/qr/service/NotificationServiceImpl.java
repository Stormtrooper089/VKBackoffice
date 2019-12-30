package com.vk.backoffice.qr.service;

import com.vk.backoffice.qr.entity.NotificationRequest;
import com.vk.backoffice.qr.repository.NotificationRequestRepository;
import com.vk.backoffice.qr.util.VankonConstant;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class NotificationServiceImpl {

    @Value("${fcm.api}")
    String uri;
    @Value("${fcm.authorization.key}")
    String token;

    @Autowired
    NotificationRequestRepository notificationRequestRepository;

    public String pushNotificationToAllUser(NotificationRequest fcmNotification) {

        System.out.println("API" + uri);
        System.out.println("Token " + token);
        LocalDate localDate = LocalDate.now();
        JSONObject msg = new JSONObject();
        msg.put("title", fcmNotification.getNotificationTitle());
        msg.put("body", fcmNotification.getNotificationMessage());
        msg.put("notificationType", "Test");
        msg.put("click_action", "FCM_PLUGIN_ACTIVITY");

        String response = callToFcmServer(msg, fcmNotification.getNotificationReceiverKey());
        if (response.contains("message_id")) {
            //fcmNotification.setNotificationCreationDate(new Date(localDate.getYear(),localDate.getMonth().getValue(),localDate.getDayOfMonth()));
            notificationRequestRepository.save(fcmNotification);
            return VankonConstant.SUCCESS;
        } else {
            return VankonConstant.FAILURE;
        }
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

    public List<NotificationRequest> getAllNotifications(){
        return notificationRequestRepository.findAll();
    }
}
