package com.medium.springsecurity15122019.init;

import com.google.common.collect.ImmutableList;
import com.medium.springsecurity15122019.domain.User;
import com.medium.springsecurity15122019.dto.VehicleDto;
import com.medium.springsecurity15122019.repository.UserRepository;
import com.medium.springsecurity15122019.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final VehicleService vehicleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        this.userRepository.saveAndFlush(
                User.builder()
                        .username("user")
                        .password(this.passwordEncoder.encode("password"))
                        .roles(ImmutableList.of("ROLE_USER"))
                        .build()
        );

        this.userRepository.saveAndFlush(
                User.builder()
                        .username("admin")
                        .password(this.passwordEncoder.encode("password"))
                        .roles(ImmutableList.of("ROLE_USER", "ROLE_ADMIN"))
                        .build()
        );

        log.debug("Printing all users");
        this.userRepository.findAll().forEach(user -> log.debug(" User: " + user.toString()));

        log.debug("Initializing vehicle data");

        ImmutableList.of("moto", "car")
                .forEach(vehicleName -> vehicleService.save(VehicleDto.of(vehicleName)));

        log.debug("Printing all vehicles: ");
        this.vehicleService.findAll().forEach(vehicle -> log.debug(" Vehicle: " + vehicle.toString()));
    }
}
