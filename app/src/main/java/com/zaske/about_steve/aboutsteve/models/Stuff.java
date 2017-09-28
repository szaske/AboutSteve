package com.zaske.about_steve.aboutsteve.models;

import org.parceler.Parcel;

/**
 * Created by steve on 9/15/2017.
 */

@Parcel
public class Stuff {
    private int mID;
    private String mName;
    private String mCause;
    private String mImgUrl;
    private int mLevel;
    private String mLink;
    private boolean mCure;

    public Stuff() {}

    public Stuff (int id, String name, String cause, String imgUrl, int Level, String link, boolean cure ){
        this.mID = id;
        this.mName = name;
        this.mCause = cause;
        this.mImgUrl = imgUrl;
        this.mLevel = Level;
        this.mLink = link;
        this.mCure = cure;
    }

    public int getID() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public String getCause() {
        return mCause;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public int getLevel() {
        return mLevel;
    }

    public String getLink() {
        return mLink;
    }

    public boolean isCure() {
        return mCure;
    }
}
