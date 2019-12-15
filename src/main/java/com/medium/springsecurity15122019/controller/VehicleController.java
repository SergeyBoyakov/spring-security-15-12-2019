package com.medium.springsecurity15122019.controller;

import com.medium.springsecurity15122019.domain.Vehicle;
import com.medium.springsecurity15122019.dto.VehicleDto;
import com.medium.springsecurity15122019.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/v1/vehicles")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity all() {
        return ok(this.vehicleService.findAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody final VehicleDto form, final HttpServletRequest request) {
        final Vehicle savedVehicle = vehicleService.save(form);

        return created(ServletUriComponentsBuilder
                .fromContextPath(request)
                .path("/v1/vehicles/{id}")
                .buildAndExpand(savedVehicle.getId())
                .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity gert(@PathVariable("id") Long id) {
        return ok(this.vehicleService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") final Long id, @RequestBody final VehicleDto form) {
        vehicleService.update(id, form);

        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        vehicleService.delete(id);

        return noContent().build();
    }
}
