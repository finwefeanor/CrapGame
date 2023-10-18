package com.finwefeanor.CrapGame.errors;

import com.finwefeanor.CrapGame.errors.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles exceptions across the entire application, ensuring uniform response structure
 * for various error scenarios.
 */
@ControllerAdvice
public class CrapGameExceptionHandler {
    /**
     * Handles validation exceptions, mapping them to a list of validation errors.
     *
     * @param ex The exception instance containing validation error details.
     * @return List of ValidationError wrapped in a ResponseEntity.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.add(new ValidationError(fieldName, errorMessage));
            }
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}





