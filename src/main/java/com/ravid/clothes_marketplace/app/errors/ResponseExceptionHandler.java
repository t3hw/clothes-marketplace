package com.ravid.clothes_marketplace.app.errors;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ravid.clothes_marketplace.server.model.ErrorResponseDTO;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
    
    // Exception handlers
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO().error("UNAUTHORIZED")
                        .exception(ex.getMessage()).status(BigDecimal.valueOf(401)));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<ErrorResponseDTO> handleUnauthorized(HttpClientErrorException ex,
            WebRequest request) {
        switch (ex.getStatusCode()) {
            case BAD_REQUEST:
                log.info("bad request: " + ex.getMessage());
                return ResponseEntity.badRequest().body(new ErrorResponseDTO().error("BAD REQUEST")
                        .exception(ex.getMessage()).status(BigDecimal.valueOf(400)));
            case UNAUTHORIZED:
                log.info("user not authorized to access api: " + ex.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO().error("UNAUTHORIZED")
                        .exception(ex.getMessage()).status(BigDecimal.valueOf(401)));
            case FORBIDDEN:
                log.info("forbidden to access api: " + ex.getMessage());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDTO().error("FORBIDDEN")
                        .exception(ex.getMessage()).status(BigDecimal.valueOf(403)));
            default:
                log.error("Uncaught HttpClientErrorException", ex);
                return ResponseEntity.internalServerError().body(new ErrorResponseDTO().error("UNEXPECTED ERROR OCCURED")
                        .exception(ex.getMessage()).status(BigDecimal.valueOf(500)));
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException exception, WebRequest request) {
        log.error("Uncaught RuntimeException", exception);
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO().error("UNEXPECTED ERROR OCCURED").exception(exception.getMessage()).status(BigDecimal.valueOf(500)));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleAllUncaughtExceptions(Exception exception, WebRequest request) {
        log.error("Unknown error occurred", exception);
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO().error("UNEXPECTED ERROR OCCURED").exception(exception.getMessage()).status(BigDecimal.valueOf(500)));
    }
    
}
