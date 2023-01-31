package com.isoft.code.stackoverflowclone.exception;

import com.isoft.code.stackoverflowclone.dto.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) throws IOException {
        String validationMessages = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(". "));
        return ResponseEntity.badRequest()
                .body(ApiResponse.builder()
                        .message(validationMessages)
                        .build()
                );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException ex) throws IOException {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ApiResponse.builder()
                        .message(ex.getMessage())
                        .build()
                );
    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public void handleBadCredentialsException(HttpServletResponse res) throws IOException {
//        res.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Login Credentials Supplied");
//    }
//

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(HttpServletResponse res, HttpServletRequest req, DataIntegrityViolationException e) throws IOException {
        String message = e.getMostSpecificCause().getMessage();
        if(message.contains("duplicate")){
            message = message.split("Detail: ")[1].split("=")[1].replaceAll("[(|)]","");
        }
        else {
            message = "Invalid Operation";
        }
        return ResponseEntity.badRequest()
                .body(ApiResponse.builder()
                        .message(message)
                        .build()
                );
    }
}
