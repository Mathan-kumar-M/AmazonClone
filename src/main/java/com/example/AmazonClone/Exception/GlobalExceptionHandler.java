package com.example.AmazonClone.Exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	 public ResponseEntity<?> handle(RuntimeException ex) {

        return ResponseEntity
                .badRequest()
                .body(
                        Map.of(
                                "error", ex.getMessage()
                        )
                );
    }

}
