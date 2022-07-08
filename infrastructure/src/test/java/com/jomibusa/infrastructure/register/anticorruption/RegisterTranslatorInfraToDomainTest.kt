package com.jomibusa.infrastructure.register.anticorruption

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithCar
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithMotorcycle
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.*

class RegisterTranslatorInfraToDomainTest {

    @Test
    fun car_parseParkingRegisterCarEntityToDomain_shouldValuePlateBeTheSame() {
        //Arrange
        val carEntity = CarEntity("HMT251")
        val parkingRegisterEntity = ParkingRegisterEntity(1L, "HMT251", Date().time)
        val registerWithCar = ParkingRegisterWithCar(carEntity, parkingRegisterEntity)

        val parseCarRegisterDomain =
            CarRegister(Car(Plate("HMT251")), Date())

        //Act
        val result =
            RegisterTranslatorInfraToDomain.parseParkingRegisterCarEntityToDomain(registerWithCar)

        //Assert
        assertThat(
            result.vehicle.plate,
            equalTo(parseCarRegisterDomain.vehicle.plate)
        )
    }

    @Test
    fun motorcycle_parseParkingRegisterCarEntityToDomain_shouldValuePlateBeTheSame() {
        //Arrange
        val motorcycleEntity = MotorcycleEntity("HMT251", 250)
        val parkingRegisterEntity = ParkingRegisterEntity(1L, "UPA19C", Date().time)
        val registerWithMotorcycle =
            ParkingRegisterWithMotorcycle(motorcycleEntity, parkingRegisterEntity)

        val parseMotorcycleRegisterDomain =
            MotorcycleRegister(Motorcycle(250, Plate("HMT251")), Date())

        //Act
        val result =
            RegisterTranslatorInfraToDomain.parseParkingRegisterMotorcycleEntityToDomain(
                registerWithMotorcycle
            )

        //Assert
        assertThat(
            result.vehicle.plate,
            equalTo(parseMotorcycleRegisterDomain.vehicle.plate)
        )
    }

}