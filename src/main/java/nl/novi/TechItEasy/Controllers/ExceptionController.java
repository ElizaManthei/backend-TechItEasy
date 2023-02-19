package nl.novi.TechItEasy.Controllers;

import nl.novi.TechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.Exceptions.RecordTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = RecordTooLongException.class)
    public ResponseEntity<Object> exception(RecordTooLongException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE);
//        HttpStatus.BAD_REQUEST ??
    }
}