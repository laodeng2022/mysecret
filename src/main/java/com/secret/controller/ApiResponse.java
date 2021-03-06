package com.secret.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.secret.entity.MyAlbum;
import com.secret.entity.VipRights;

import java.util.List;

public final class ApiResponse<T> {
    private T data;
    private String code;
    private String msg;

    private ApiResponse(String code, String msg, T data) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    @JsonCreator
    public static <T> ApiResponse<T> success(
            @JsonProperty("data") T data) {
        return new ApiResponse<>(Code.SUCCESS.getCode(), "", data);
    }

    @JsonCreator
    public static <T> ApiResponse<T> error(
            @JsonProperty("msg") String msg,
            @JsonProperty("data") T data) {
        return new ApiResponse<>(Code.ERROR.getCode(), msg, data);
    }

    public enum Code {
        SUCCESS("success"), ERROR("error");

        private final String code;

        Code(String code) {
            this.code = code;
        }

        @JsonValue
        public String getCode() {
            return this.code;
        }
    }

    public Object getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }
    public String getMsg(){return this.msg;}
}
