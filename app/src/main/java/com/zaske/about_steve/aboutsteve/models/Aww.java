package com.zaske.about_steve.aboutsteve.models;


import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by Guest on 9/20/17.
 */

@Parcel
public class Aww implements Serializable {
    private String kind;
    private String id;
    private String title;
    private String thumbnail;
    private String url;
    private String pushId;

    public Aww(){}

    public Aww(String kind, String id, String title, String thumbnail, String url) {
        this.kind = kind;
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.url = url;
    }

    public String getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) { this.pushId = pushId; }
}
