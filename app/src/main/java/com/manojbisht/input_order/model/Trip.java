package com.manojbisht.input_order.model;

public class Trip {
    private String inputTripNumber;
    private String inputTripImage;
    private String inputTripType;
    private String inputTripDetail;
    private String getInputTripPrice;

    public Trip() {
    }

    public Trip(String inputTripNumber, String inputTripImage, String inputTripType, String inputTripDetail, String getInputTripPrice) {
        this.inputTripNumber = inputTripNumber;
        this.inputTripImage = inputTripImage;
        this.inputTripType = inputTripType;
        this.inputTripDetail = inputTripDetail;
        this.getInputTripPrice = getInputTripPrice;
    }

    public String getInputTripNumber() {
        return inputTripNumber;
    }

    public void setInputTripNumber(String inputTripNumber) {
        this.inputTripNumber = inputTripNumber;
    }

    public String getInputTripType() {
        return inputTripType;
    }

    public void setInputTripType(String inputTripType) {
        this.inputTripType = inputTripType;
    }

    public String getInputTripDetail() {
        return inputTripDetail;
    }

    public void setInputTripDetail(String inputTripDetail) {
        this.inputTripDetail = inputTripDetail;
    }

    public String getGetInputTripPrice() {
        return getInputTripPrice;
    }

    public void setGetInputTripPrice(String getInputTripPrice) {
        this.getInputTripPrice = getInputTripPrice;
    }

    public String getInputTripImage() {
        return inputTripImage;
    }

    public void setInputTripImage(String inputTripImage) {
        this.inputTripImage = inputTripImage;
    }
}
