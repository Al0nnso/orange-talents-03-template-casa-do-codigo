package br.com.zupacademy.alonso.casadocodigo.controller.dto;

import java.util.Date;

public class ErrorMessage {
    private Date timestamp;
    private String title;
    private String message;

    public ErrorMessage(){}

    public ErrorMessage(Date timestamp,String title, String message){
        this.timestamp = timestamp;
        this.title = title;
        this.message = message;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
