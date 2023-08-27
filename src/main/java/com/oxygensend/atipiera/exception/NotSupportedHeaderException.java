package com.oxygensend.atipiera.exception;

import com.oxygensend.atipiera.exception.api.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotSupportedHeaderException extends ApiException {

    public NotSupportedHeaderException(String header) {
        super("Header " + header + " is not supported. Please use Accept: application/json.");
    }
}
