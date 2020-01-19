package com.vk.backoffice.qr.model;

public class UserStat {
    boolean isActive;
    Long count;

    public UserStat(boolean isActive, Long count) {
        this.isActive = isActive;
        this.count = count;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
