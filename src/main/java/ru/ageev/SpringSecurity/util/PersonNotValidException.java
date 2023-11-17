package ru.ageev.SpringSecurity.util;

public class PersonNotValidException extends RuntimeException {

    private final String message;
    private final long time;

    public PersonNotValidException(String message, long time) {
        this.message = message;
        this.time = time;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }
}
