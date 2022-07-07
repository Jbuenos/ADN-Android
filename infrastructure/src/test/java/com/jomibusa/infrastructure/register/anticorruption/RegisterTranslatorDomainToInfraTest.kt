package com.jomibusa.infrastructure.register.anticorruption

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.*

class RegisterTranslatorDomainToInfraTest {

    @Test
    fun parseParkingRegisterDomainToEntity_shouldValuePlateBeTheSame() {

        //Arrange
        val registerDomain = CarRegister(Car(Plate("HMT251")), Date())

        val parseRegisterEntity =
            ParkingRegisterEntity(idPlateVehicle = "HMT251", initDate = Date().time)

        //Act
        val registerEntity =
            RegisterTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(registerDomain)

        //Assert
        assertThat(
            registerEntity.idPlateVehicle,
            equalTo(parseRegisterEntity.idPlateVehicle)
        )

    }

}