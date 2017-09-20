package com.zaske.about_steve.aboutsteve.models;


/**
 * Created by Guest on 9/20/17.
 */

public class Aww {
    private String mKind;
    private String mId;
    private String mTitle;
    private String mThumbnail;
    private String mUrl;

    public Aww(){}

    public Aww(String mKind, String mId, String mTitle, String mThumbnail, String mUrl) {

        this.mKind = mKind;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mThumbnail = mThumbnail;
        this.mUrl = mUrl;
    }

    public String getKind() {
        return mKind;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public String getUrl() {
        return mUrl;
    }
}
