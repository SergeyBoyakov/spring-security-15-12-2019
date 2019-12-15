package com.medium.springsecurity15122019.service

import com.medium.springsecurity15122019.domain.Vehicle
import com.medium.springsecurity15122019.dto.VehicleDto
import spock.lang.Specification

class VehicleMapperSpec extends Specification {

    def 'should map entity to dto'() {
        given:
        def vehicle = new Vehicle(id: 1L, name: "testName")

        when:
        def vehicleDto = new VehicleMapper().toDto(vehicle)

        then:
        vehicleDto.name == "testName"
    }

    def 'should map collection of entities to dtos'() {
        given:
        final String FIRST_NAME = "firstVehicle"
        final String SECOND_NAME = "secondVehicle"
        def firstVehicle = new Vehicle(id: 1L, name: FIRST_NAME)
        def secondVehicle = new Vehicle(id: 2L, name: SECOND_NAME)

        when:
        List<VehicleDto> vehicleDtos = new VehicleMapper().toDtos([firstVehicle, secondVehicle])

        then:
        vehicleDtos.size() == 2
        vehicleDtos.any {
            it.name == FIRST_NAME
        }
        vehicleDtos.any {
            it.name == SECOND_NAME
        }
    }

    def 'should map to entity'() {
        given:
        final String NAME = "vehicle name"
        def vehicleDto = new VehicleDto(name: NAME)

        when:
        def entity = new VehicleMapper().toEntity(vehicleDto)

        then:
        entity.name == NAME
    }
}
