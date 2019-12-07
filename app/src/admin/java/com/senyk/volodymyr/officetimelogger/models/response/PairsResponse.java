package com.senyk.volodymyr.officetimelogger.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PairsResponse {
    @SerializedName("pairs")
    @Expose
    private List<PairResponse> pairs = null;

    public List<PairResponse> getPairs() {
        return pairs;
    }

    public void setPairs(List<PairResponse> pairs) {
        this.pairs = pairs;
    }
}
