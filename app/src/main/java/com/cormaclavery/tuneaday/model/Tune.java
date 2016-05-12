package com.cormaclavery.tuneaday.model;

import com.google.gson.annotations.SerializedName;

public class Tune {

    @SerializedName("tune")
    private String mTune;
    @SerializedName("setting")
    private String mSetting;
    @SerializedName("name")
    private String mName;
    @SerializedName("type")
    private String mType;
    @SerializedName("meter")
    private String mMeter;
    @SerializedName("mode")
    private String mMode;
    @SerializedName("abc")
    private String mAbc;
    private String mVideoId;

    public Tune() {

    }

    public Tune(String name, String abc) {
        mName = name;
        mAbc = abc;
    }

    public String getTune() {
        return mTune;
    }

    public void setTune(String tune) {
        mTune = tune;
    }

    public String getSetting() {
        return mSetting;
    }

    public void setSetting(String setting) {
        mSetting = setting;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getMeter() {
        return mMeter;
    }

    public void setMeter(String meter) {
        mMeter = meter;
    }

    public String getMode() {
        return mMode;
    }

    public void setMode(String mode) {
        mMode = mode;
    }

    public String getAbc() {
        return mAbc;
    }

    public void setAbc(String abc) {
        mAbc = abc;
    }

    public String getVideoId() {
        return mVideoId;
    }

    public void setVideoId(String videoId) {
        mVideoId = videoId;
    }

    //This will return the name of the tune and the type with '+' instead of ' ' so it can be used in a search query
    public String getSearchQuery() {
        String result = mName + " " + mType;
        for (int i = 0; i < result.length(); i++) {
            if (result.substring(i, i + 1).equals(" ")) {
                result = result.substring(0, i) + "+" + result.substring(i + 1);
            }
        }

        return result;
    }

}
