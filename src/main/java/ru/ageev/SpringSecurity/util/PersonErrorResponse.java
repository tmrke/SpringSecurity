package ru.ageev.SpringSecurity.util;

import java.util.Date;

public class PersonErrorResponse {
    private String message;
    private Date errorTime;

    public PersonErrorResponse(String message, long errorTime) {
        this.message = message;
        this.errorTime = errorTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Date errorTime) {
        this.errorTime = errorTime;
    }

}
