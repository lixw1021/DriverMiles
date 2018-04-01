package com.xianwei.drivermiles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xianwei li on 3/31/2018.
 */

public class DataModel {
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("icon_emoji")
    @Expose
    private String iconEmoji;

    public DataModel(String text) {
        channel = "#Android";
        username = "Xianwei Li";
        iconEmoji = ":ghost:";
        //"text": "Name: <your name> - Latitude: <latitude>, Longitude: <longitude>."
        this.text = text;
    }
}
