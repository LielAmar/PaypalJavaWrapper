package com.lielamar.paypalwrapper.exceptions;

import com.lielamar.paypalwrapper.requests.Request;

public class RequestExecutionException extends Exception {

    public RequestExecutionException(Request request) {
        super("An Error occurred while execution the following request: " + request.getClass().getName()
            + "\nPlease make sure your credentials and variables are correct!");
    }
}
