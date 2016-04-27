package com.cormaclavery.tuneaday.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TuneBook {

    public List<Tune> getTuneList() {
        return mTuneList;
    }

    public void setTuneList(List<Tune> tuneList) {
        mTuneList = tuneList;
    }

    @SerializedName("tunes")
    private List<Tune> mTuneList;

    public Tune getTuneAtIndex(int index){
        return  mTuneList.get(index);
    }

}
