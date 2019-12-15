package com.medium.springsecurity15122019.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String message) {
        super(message);
    }
}
