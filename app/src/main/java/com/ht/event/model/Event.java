package com.ht.event.model;

import android.content.ClipData;

import java.io.Serializable;
import java.util.ArrayList;


public class Event implements Serializable

{
    public int id;
    public String tag2;
    public String tag1;
    public int image;
    public String time;
    public String title;
    public String venue;
    public String price;
    public String lat;
    public String lng;
    public String venueAddress;
    public String discription;
    public String organisationName;
    private boolean is_bookmarked;
    private boolean is_registered;
    public String startTime;
    public String endTime;
    public String TicketId;

    public String getTicketId() {
        return TicketId;
    }

    public void setTicketId(String ticketId) {
        TicketId = ticketId;
    }

    private ArrayList<Session>sessions;

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public boolean is_registered() {
        return is_registered;
    }

    public void setIs_registered(boolean is_registered) {
        this.is_registered = is_registered;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }



    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }



    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }


    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public boolean is_bookmarked() {
        return is_bookmarked;
    }

    public void setIs_bookmarked(boolean is_bookmarked) {
        this.is_bookmarked = is_bookmarked;
    }



    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }



    public String getTag2() {
        return tag2;
    }

    public String getTag1() {
        return tag1;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



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

    @Override
    public boolean equals(Object o) {
        if( ((Event) o).getId() == this.id) {
            return true;
        }
        return  false;
    }
}
