package ru.ageev.SpringSecurity.util;

public class PersonErrorResponse {
    private String message;
    private long errorTime;

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

    public long getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(long errorTime) {
        this.errorTime = errorTime;
    }

}
