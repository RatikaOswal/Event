package com.ht.event.model;


import android.widget.TextView;

import java.io.Serializable;

public class User implements Serializable{

  private String Name;
    private String Email;
    private String PhoneNo;
    private String Organisation;
    private String OrgWebsite;
    private String Address;
    private String Image;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }



    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getOrganisation() {
        return Organisation;
    }

    public void setOrganisation(String organisation) {
        Organisation = organisation;
    }

    public String getOrgWebsite() {
        return OrgWebsite;
    }

    public void setOrgWebsite(String orgWebsite) {
        OrgWebsite = orgWebsite;
    }

    public String getName() {
        return Name;
    }

    public TextView setName(String name) {
        Name = name;
        return null;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



}
