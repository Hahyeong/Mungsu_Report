package com.ewha.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

public class movementdata {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("DATE")
    private Date DATE;

    @SerializedName("TIME")
    private Time TIME;

    @SerializedName("movement")
    private int movement;

    @SerializedName("result")
    private List<movementdata> result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Date getDATE() { return DATE; }

    public Time getTIME() { return TIME; }

    public int getMovement() { return movement; }

    public List<movementdata> getResult() { return result; }
}
