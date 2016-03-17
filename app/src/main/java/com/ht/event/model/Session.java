package com.ht.event.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Session implements Serializable {
    private String title;
    private String venue;
    private String price;
    private String startTime;
    private String endTime;
    private ArrayList<Speaker> speakerList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Speaker> getSpeakerList() {
        return speakerList;
    }

    public void setSpeakerList(ArrayList<Speaker> speakerList) {
        this.speakerList = speakerList;
    }


}
