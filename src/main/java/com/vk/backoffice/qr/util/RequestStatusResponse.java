package com.vk.backoffice.qr.util;

public class RequestStatusResponse {

    private String responseStatus ;
    private String responseStatusDescription;
    private String transactionID;
    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseStatusDescription() {
        return responseStatusDescription;
    }

    public void setResponseStatusDescription(String responseStatusDescription) {
        this.responseStatusDescription = responseStatusDescription;
    }

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	@Override
	public String toString() {
		return "RequestStatusResponse [responseStatus=" + responseStatus + ", responseStatusDescription="
				+ responseStatusDescription + ", transactionID=" + transactionID + "]";
	}
    
}
