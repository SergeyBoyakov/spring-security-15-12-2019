package com.medium.springsecurity15122019.dto;

import com.medium.springsecurity15122019.domain.Vehicle;
import com.medium.springsecurity15122019.service.VehicleMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {
    private String name;

    public static VehicleDto of(String name) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setName(name);

        return vehicleDto;
    }
}
