package com.Journal.journalApp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {

    @ExceptionHandler(value = JournalNotfoundException.class)
    public ResponseEntity<Object> handleException(JournalNotfoundException e) {
        return new ResponseEntity<>("Journal Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidHeaderFieldException.class)
    public ResponseEntity<String> handleInvalidHeaderFieldException(InvalidHeaderFieldException e){
        return new ResponseEntity<>("Invalid Header Field", HttpStatus.PRECONDITION_FAILED);
    }
}
