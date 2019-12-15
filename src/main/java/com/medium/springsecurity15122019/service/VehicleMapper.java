package com.medium.springsecurity15122019.service;

import com.medium.springsecurity15122019.domain.Vehicle;
import com.medium.springsecurity15122019.dto.VehicleDto;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleMapper {
    public VehicleDto toDto(final Vehicle vehicle) {
        if (vehicle == null)
            return null;

        val vehicleDto = new VehicleDto();
        vehicleDto.setName(vehicle.getName());

        return vehicleDto;
    }

    public List<VehicleDto> toDtos(final List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Vehicle toEntity(VehicleDto dto) {
        if (dto == null)
            return null;

        return Vehicle.builder().name(dto.getName()).build();
    }
}
