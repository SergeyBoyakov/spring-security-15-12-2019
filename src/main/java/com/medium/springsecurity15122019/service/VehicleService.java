package com.medium.springsecurity15122019.service;


import com.medium.springsecurity15122019.domain.Vehicle;
import com.medium.springsecurity15122019.dto.VehicleDto;
import com.medium.springsecurity15122019.exception.VehicleNotFoundException;
import com.medium.springsecurity15122019.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public List<VehicleDto> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicleMapper.toDtos(vehicles);
    }

    public Vehicle save(VehicleDto dto) {
        Vehicle vehicle = vehicleMapper.toEntity(dto);

        return vehicleRepository.save(vehicle);
    }

    public Vehicle findById(long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id: " + id + " not found"));
    }

    public Vehicle update(long id, VehicleDto form) {
        Vehicle existing = this.findById(id);
        existing.setName(form.getName());

        return this.vehicleRepository.save(existing);
    }

    public void delete(long id) {
        vehicleRepository.deleteById(id);
    }
}


