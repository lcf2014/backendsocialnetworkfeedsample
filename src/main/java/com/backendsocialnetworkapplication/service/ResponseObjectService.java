package com.backendsocialnetworkapplication.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseObjectService {
    private String status;
    private String message;
    private Object payload;

    public ResponseObjectService(String status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public ResponseObjectService() {
    }
    
}
