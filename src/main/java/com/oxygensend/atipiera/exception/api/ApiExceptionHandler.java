package com.oxygensend.atipiera.exception.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getStatusCodeValue(), ex.getMessage());
        return buildExceptionResponse(exceptionResponse);
    }

    private ResponseEntity<Object> buildExceptionResponse(ExceptionResponse exceptionResponse) {
        log.error("Exception occurred: {}", exceptionResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(exceptionResponse, headers, exceptionResponse.statusCode());
    }
}
