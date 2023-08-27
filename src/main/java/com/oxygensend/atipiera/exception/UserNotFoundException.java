package com.oxygensend.atipiera.exception;

import com.oxygensend.atipiera.exception.api.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ApiException {

    public UserNotFoundException(String username) {
        super("User with " + username + " not found on github.");
    }

}
