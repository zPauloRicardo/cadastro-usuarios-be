package me.paulojr.cadastro.infra.api.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import me.paulojr.cadastro.domain.shared.exceptions.DomainException;
import me.paulojr.cadastro.domain.shared.exceptions.InternalErrorException;
import me.paulojr.cadastro.domain.shared.exceptions.NotFoundException;
import me.paulojr.cadastro.domain.shared.exceptions.NotificationException;
import me.paulojr.cadastro.domain.shared.validation.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<CustomErrorResponse> handleNotificationException(NotificationException ex) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getErrors().stream().map(ErrorResponse::from).toList()
        );
        log.warn(ex.getMessage(), ex.getCause());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<CustomErrorResponse> handleInternalErrorException(InternalErrorException ex) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                List.of(ErrorResponse.from(new Error(ex.getMessage())))
        );
        log.warn(ex.getMessage(), ex.getCause());

        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException ex) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                null
        );
        log.warn(ex.getMessage(), ex.getCause());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ExceptionHandler(DomainException.class)
    public ResponseEntity<CustomErrorResponse> handleDomainException(DomainException ex) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                null
        );
        log.warn(ex.getMessage(), ex.getCause());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record CustomErrorResponse(
            @JsonProperty("message")
            String message,
            @JsonProperty("status")
            int status,
            @JsonProperty("errors")
            List<ErrorResponse> errors
    ) {
    }

    public record ErrorResponse(
            @JsonProperty("message")
            String message
    ) {
        public static ErrorResponse from(Error error) {
            return new ErrorResponse(error.message());
        }
    }

}
