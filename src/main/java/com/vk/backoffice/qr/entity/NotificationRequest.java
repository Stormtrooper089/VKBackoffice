package com.vk.backoffice.qr.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notifications")
public class NotificationRequest {

    @Id
    @Column(name = "notificationId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;

    @Column(name = "notificationTitle")
    private String notificationTitle;

    @Column(name = "notificationMessage")
    private String notificationMessage;

    @Column(name = "notificationReceiverKey")
    private String notificationReceiverKey;

    @Column(name = "notificationCreationDate")
    private Date notificationCreationDate;

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getNotificationReceiverKey() {
        return notificationReceiverKey;
    }

    public void setNotificationReceiverKey(String notificationReceiverKey) {
        this.notificationReceiverKey = notificationReceiverKey;
    }

    public Date getNotificationCreationDate() {
        return notificationCreationDate;
    }

    public void setNotificationCreationDate(Date notificationCreationDate) {
        this.notificationCreationDate = notificationCreationDate;
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "notificationId=" + notificationId +
                ", notificationTitle='" + notificationTitle + '\'' +
                ", notificationMessage='" + notificationMessage + '\'' +
                ", notificationReceiverKey='" + notificationReceiverKey + '\'' +
                ", notificationCreationDate=" + notificationCreationDate +
                '}';
    }
}
