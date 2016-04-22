package com.cormaclavery.tuneaday.tunes;

public class Tune {

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
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

    private String mId;
    private String mName;
    private String mType;
    private String mMeter;
    private String mMode;
    private String mAbc;

    public Tune(){

    }

    public Tune(String name, String abc){
        mName = name;
        mAbc = abc;
    }

}
