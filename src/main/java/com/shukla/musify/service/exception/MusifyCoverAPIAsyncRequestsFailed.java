package com.shukla.musify.service.exception;

import com.shukla.musify.base.error.AMusifyBaseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static com.shukla.musify.base.error.MusifyErrorCode.COVER_API_ASYNC_REQUESTS_FAILED;

public class MusifyCoverAPIAsyncRequestsFailed extends AMusifyBaseException {
    public MusifyCoverAPIAsyncRequestsFailed(UUID id, Exception ex) {
        super(COVER_API_ASYNC_REQUESTS_FAILED, HttpStatus.UNPROCESSABLE_ENTITY, ex, id.toString());
    }
}
