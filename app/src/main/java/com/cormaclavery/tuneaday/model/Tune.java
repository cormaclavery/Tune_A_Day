package com.cormaclavery.tuneaday.model;

import com.google.gson.annotations.SerializedName;

public class Tune {

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

    public Tune(){

    }

    public Tune(String name, String abc){
        mName = name;
        mAbc = abc;
    }


}
