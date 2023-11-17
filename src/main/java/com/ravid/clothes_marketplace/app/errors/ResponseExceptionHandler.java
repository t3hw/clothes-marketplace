package com.ravid.clothes_marketplace.app.errors;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ravid.clothes_marketplace.server.model.ErrorResponseDTO;

import lombok.extern.log4j.Log4j2;


@RestControllerAdvice
@Log4j2
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
    

    // Exception handlers

    @ExceptionHandler(JWTVerificationException.class)
    public final ResponseEntity<ErrorResponseDTO> handleJwtErrors(JWTVerificationException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO().error("UNAUTHORIZED")
                        .exception(ex.getMessage()).status(401));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO().error("UNAUTHORIZED")
                        .exception(ex.getMessage()).status(401));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleAllUncaughtExceptions(Exception exception, WebRequest request) {
        log.error("Unknown error occurred", exception);
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO().error("UNEXPECTED ERROR OCCURED").exception(exception.getMessage()).status(500));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException exception, WebRequest request) {
        if (exception instanceof UserException) {
            return manageUserException((UserException) exception, request);
        } else {
            log.error("Uncaught RuntimeException", exception);
            return ResponseEntity.internalServerError().body(new ErrorResponseDTO().error("UNEXPECTED ERROR OCCURED").exception(exception.getMessage()).status(500));
        }
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO().error("BAD REQUEST")
                                .exception(ex.getMessage()).status(400));
    }

    // Construct dynamic error response
    protected ResponseEntity<ErrorResponseDTO> manageUserException(UserException ex,
            WebRequest request) {
        if (ex.getCause() instanceof HttpClientErrorException)
            switch (((HttpClientErrorException) ex.getCause()).getStatusCode()) {
                case BAD_REQUEST:
                    log.info("bad request: " + ex.getMessage());
                    return ResponseEntity.badRequest().body(new ErrorResponseDTO().error("BAD REQUEST")
                            .exception(ex.getMessage()).status(400));
                case UNAUTHORIZED:
                    log.info("user not authorized to access api: " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO().error("UNAUTHORIZED")
                            .exception(ex.getMessage()).status(401));
                case FORBIDDEN:
                    log.info("forbidden to access api: " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDTO().error("FORBIDDEN")
                            .exception(ex.getMessage()).status(403));
                case NOT_FOUND:
                    log.info("Resurce not found: " + ex.getMessage());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO().error("NOT FOUND")
                            .exception(ex.getMessage()).status(404));
                default:
                    log.error("Uncaught HttpClientErrorException", ex);
                    return ResponseEntity.internalServerError().body(new ErrorResponseDTO().error("UNEXPECTED ERROR OCCURED")
                            .exception(ex.getMessage()).status(500));
        } else {
            log.error("Uncaught UserException", ex);
            return ResponseEntity.internalServerError().body(new ErrorResponseDTO().error("UNEXPECTED ERROR OCCURED")
                    .exception(ex.getMessage()).status(500));
        }
    }
}
