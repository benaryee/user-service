package org.tech11.model.enums;


public enum ServiceError {
    USER_NOT_FOUND(1001, "User not found"),
    USER_ALREADY_EXISTS(1002, "User already exists"),
    USER_EMAIL_ALREADY_EXISTS(1003, "User email already exists"),
    PASSWORD_MISMATCH(1004, "User passwords don't match");
    private final int code;
    private final String message;
    ServiceError(int code , String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage() {
        return message;
    }
}
