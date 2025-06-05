package org.example.demo1.dto.common;



import java.util.List;


public class CommonResponse<T> {
    private T body;
    private boolean success;
    private List<String> messageDetails;

    private int errorCode;


    public CommonResponse(List<String> messageDetails, int errorCode) {
        this.messageDetails = messageDetails;
        this.errorCode = errorCode;
    }

    public CommonResponse(T body) {
        this.body = body;
        this.success = true;
    }

    public CommonResponse(T body, boolean success, List<String> messageDetails, int errorCode) {
        this.body = body;
        this.success = success;
        this.messageDetails = messageDetails;
        this.errorCode = errorCode;
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

    public int getErrorCode() {
        return errorCode;
    }
}