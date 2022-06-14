package com.shukla.musify.base.exception;

import com.shukla.musify.base.error.AMusifyBaseException;
import org.springframework.web.client.HttpClientErrorException;

import static com.shukla.musify.base.error.MusifyErrorCode.SERVICE_COMMUNICATION_EXCEPTION;

public class MusifyServiceCommunicationException extends AMusifyBaseException {

    public MusifyServiceCommunicationException(String serviceName, HttpClientErrorException e) {
        super(SERVICE_COMMUNICATION_EXCEPTION, e.getStatusCode(), e, serviceName, e.getStatusCode().toString());
    }
}
