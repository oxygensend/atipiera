package com.oxygensend.atipiera.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        ResponseStatus responseStatus = this.getClass().getAnnotation(ResponseStatus.class);
        return responseStatus.value();
    }

    public int getStatusCodeValue() {
        return getStatusCode().value();
    }

}
