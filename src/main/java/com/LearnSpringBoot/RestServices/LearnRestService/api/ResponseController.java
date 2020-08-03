package com.LearnSpringBoot.RestServices.LearnRestService.api;


import com.LearnSpringBoot.RestServices.LearnRestService.Entity.ExceptionResponse;
import com.LearnSpringBoot.RestServices.LearnRestService.UserDefinedException.UserNotFoundException;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ResponseController extends ResponseEntityExceptionHandler {

     @ExceptionHandler(Exception.class)
     public final ResponseEntity handleAllException(Exception ex, WebRequest request){
         ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
     }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity handleUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

/*    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity handleNoHandlerFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NO_CONTENT);
    }*/
/*    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError noHandlerFoundException(
            NoHandlerFoundException ex) {

        int code = 1000;
        String message = "No handler found for your request.";
        re*/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failure", ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}