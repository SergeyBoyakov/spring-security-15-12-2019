package com.medium.springsecurity15122019.controller.exceptionhandler;

import com.medium.springsecurity15122019.domain.Vehicle;
import com.medium.springsecurity15122019.exception.VehicleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class RestExcpetionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity vehicleNotFound(VehicleNotFoundException ex, WebRequest request) {
        log.debug("Handling: " + ex.getMessage());

        return ResponseEntity.notFound().build();
    }
}
