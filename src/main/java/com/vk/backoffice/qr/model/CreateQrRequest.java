package com.vk.backoffice.qr.model;


public class CreateQrRequest {
    private String productId;
    private int numberOfQrCodeRequired;
    private int pointsForCode;
    private int activationStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getNumberOfQrCodeRequired() {
        return numberOfQrCodeRequired;
    }

    public void setNumberOfQrCodeRequired(int numberOfQrCodeRequired) {
        this.numberOfQrCodeRequired = numberOfQrCodeRequired;
    }

    public int getPointsForCode() {
        return pointsForCode;
    }

    public void setPointsForCode(int pointsForCode) {
        this.pointsForCode = pointsForCode;
    }

    public int getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(int activationStatus) {
        this.activationStatus = activationStatus;
    }

    @Override
    public String toString() {
        return "CreateQrRequest{" +
                "productId='" + productId + '\'' +
                ", numberOfQrCodeRequired=" + numberOfQrCodeRequired +
                ", pointsForCode=" + pointsForCode +
                ", activationStatus=" + activationStatus +
                '}';
    }
}
