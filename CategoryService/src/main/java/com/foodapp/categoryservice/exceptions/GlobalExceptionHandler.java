package com.foodapp.categoryservice.exceptions;

import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(CategoryException exc, WebRequest req){

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
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ErrorDetails> busExceptionHandler(BadCredentialsException exc, WebRequest req){
//
//        ErrorDetails errorDetails = new ErrorDetails();
//
//        errorDetails.setTimeSpan(LocalDateTime.now());
//        errorDetails.setMessage(exc.getMessage());
//        errorDetails.setDescription(req.getDescription(false));
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> genericExceptionHandler(Exception exc, WebRequest req){

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setDescription(req.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException me, WebRequest req)  {

        ErrorDetails errorDetails=new ErrorDetails();

        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setDescription(req.getDescription(false));
        errorDetails.setMessage(me.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException exc, WebRequest req)  {

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimeSpan(LocalDateTime.now());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setDescription(req.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }


}
