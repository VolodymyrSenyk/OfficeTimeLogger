package com.senyk.volodymyr.officetimelogger.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LogResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("logs")
    @Expose
    private List<TimeLog> logs = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TimeLog> getLogs() {
        return logs;
    }

    public void setLogs(List<TimeLog> logs) {
        this.logs = logs;
    }
}
