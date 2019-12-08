package com.vk.backoffice.qr.model;

public class Statistic {
    int key;
    int value;

    public Statistic() {
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    public Statistic(int currentKey, int i) {
        this.key = currentKey;
        this.value = i;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
