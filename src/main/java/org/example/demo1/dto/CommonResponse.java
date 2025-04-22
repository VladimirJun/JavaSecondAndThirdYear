package org.example.demo1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class CommonResponse<T> {
    private T body;
    private boolean success;
    private List<String> messageDetails;

    private int keyError;


    public CommonResponse(List<String> messageDetails, int keyError) {
        this.messageDetails = messageDetails;
        this.keyError = keyError;
    }

    public CommonResponse(T body) {
        this.body = body;
        this.success = true;
    }

    public CommonResponse(T body, boolean success, List<String> messageDetails, int keyError) {
        this.body = body;
        this.success = success;
        this.messageDetails = messageDetails;
        this.keyError = keyError;
    }

    public T getBody() {
        return body;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<String> getMessageDetails() {
        return messageDetails;
    }

    public int getKeyError() {
        return keyError;
    }
}