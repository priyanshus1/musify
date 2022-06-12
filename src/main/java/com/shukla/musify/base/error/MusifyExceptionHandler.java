package com.shukla.musify.base.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MusifyExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MusifyExceptionHandler.class);

    @ExceptionHandler(AMusifyBaseException.class)
    @ResponseBody
    public final ResponseEntity<ErrorData> handleMusifyException(AMusifyBaseException exception) {
        ErrorData errorData = exception.getErrorData();
        LOG.error(errorData.getErrorLogMessage(), exception);
        return getErrorDataResponseEntity(errorData);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public final ResponseEntity<ErrorData> handleException(RuntimeException exception) {
        logError(exception);
        ErrorData errorData = new ErrorData(MusifyErrorCode.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
        return getErrorDataResponseEntity(errorData);
    }

    private void logError(RuntimeException exception) {
        LOG.error(exception.getMessage(), exception);
    }

    private ResponseEntity<ErrorData> getErrorDataResponseEntity(ErrorData errorData) {
        return new ResponseEntity<>(errorData, errorData.getHttpStatus());
    }
}
