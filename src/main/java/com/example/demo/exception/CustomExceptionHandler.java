package com.example.demo.exception;

import com.mongodb.MongoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.logging.Logger;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = Logger.getLogger(CustomExceptionHandler.class.getName());


    @ExceptionHandler(IOException.class)
    public final ResponseEntity<CustomException> ioExceptionHandler(IOException ex){
        logger.info("in ioExceptionHandler");
        CustomException exceptionResponse= new CustomException(ex.getMessage()," JSON parsing failed while retreiving product name");
        return new ResponseEntity<CustomException>(exceptionResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MongoException.class)
    public final ResponseEntity<CustomException> ioExceptionHandler(MongoException ex){
        logger.info("in MongoExceptionHandler");
        CustomException exceptionResponse= new CustomException(ex.getMessage()," Price details are not avaliable in MongoDB");
        return new ResponseEntity<CustomException>(exceptionResponse,new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<CustomException> ioExceptionHandler(HttpClientErrorException ex){
        logger.info("in MongoExceptionHandler");
        CustomException exceptionResponse= new CustomException(ex.getMessage()," Product details are not available for specified id");
        return new ResponseEntity<CustomException>(exceptionResponse,new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomException> exceptionHandler(Exception ex){
        logger.info("in exceptionHandler");
        CustomException exceptionResponse= new CustomException(ex.getMessage(),ex.getMessage());
        return new ResponseEntity<CustomException>(exceptionResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
}
