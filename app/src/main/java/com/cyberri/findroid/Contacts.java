package com.cyberri.findroid;

/**
 * Created by It's me! on 17-05-2016.
 */
import java.io.Serializable;

/**
 * Created by abhinav on 7/10/15.
 */
public class Contacts implements Serializable{

    private String mName,mEmail,mNumber;
    private int mImageId;

    public Contacts(String name, String email, String number, int imageId) {
        this.mName = name;
        this.mEmail = email;
        this.mNumber = number;
        this.mImageId = imageId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        this.mImageId = imageId;
    }
}
