package com.foodapp.restaurantservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<ErrorDetails> restaurantExceptionHandler(RestaurantException exc, WebRequest req){

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setDescription(req.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ErrorDetails> itemExceptionHandler(ItemException exc, WebRequest req){

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setDescription(req.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDetails> sqlExceptionHandler(SQLException exc, WebRequest req){

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setDescription(req.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> genericExceptionHandler(Exception exc, WebRequest req){

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setDescription(req.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException me, WebRequest req)  {
//
//        ErrorDetails errorDetails=new ErrorDetails();
//
//        errorDetails.setTimeSpan(LocalDateTime.now());
//        errorDetails.setDescription(req.getDescription(false));
//        errorDetails.setMessage(me.getBindingResult().getFieldError().getDefaultMessage());
//
//        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
//
//    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException me, WebRequest req)  {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setDescription(req.getDescription(false));

        // Check if getFieldError() returns null
        FieldError fieldError = me.getBindingResult().getFieldError();
        if (fieldError != null) {
            errorDetails.setMessage(fieldError.getDefaultMessage());
        } else {
            // Handle the case where no field error is present
            errorDetails.setMessage("Validation failed");
        }

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException exc, WebRequest req)  {

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setDescription(req.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }
    
//  @ExceptionHandler(CallNotPermittedException.class)
//  public ResponseEntity<ErrorDetails> callNotPermitedExceptionHandler(CallNotPermittedException exc, WebRequest req){
//
//      ErrorDetails errorDetails = new ErrorDetails();
//
//      errorDetails.setTimeSpan(LocalDateTime.now());
//      errorDetails.setMessage("Connection is open and does not permit any more calls, please try again after sometime");
//      errorDetails.setDescription(req.getDescription(false));
//
//      return new ResponseEntity<>(errorDetails, HttpStatus.SERVICE_UNAVAILABLE);
//
//  }
//
//  @ExceptionHandler(RequestNotPermitted.class)
//  public ResponseEntity<ErrorDetails> handleRequestNotPermitted(RequestNotPermitted exc, WebRequest req) {
//
//      ErrorDetails errorDetails = new ErrorDetails();
//
//      errorDetails.setTimeSpan(LocalDateTime.now());
//      errorDetails.setMessage("Request limit exceeded");
//      errorDetails.setDescription(req.getDescription(false));
//
//      return new ResponseEntity<>(errorDetails, HttpStatus.TOO_MANY_REQUESTS);
//
//  }
//  @ExceptionHandler(BadCredentialsException.class)
//  public ResponseEntity<ErrorDetails> busExceptionHandler(BadCredentialsException exc, WebRequest req){
//
//      ErrorDetails errorDetails = new ErrorDetails();
//
//      errorDetails.setTimeSpan(LocalDateTime.now());
//      errorDetails.setMessage(exc.getMessage());
//      errorDetails.setDescription(req.getDescription(false));
//
//      return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//
//  }


}
