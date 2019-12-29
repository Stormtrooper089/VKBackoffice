package com.vk.backoffice.qr.model;

import org.springframework.stereotype.Component;

@Component
public class NotificationData {
    String badge;
    String color;

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
