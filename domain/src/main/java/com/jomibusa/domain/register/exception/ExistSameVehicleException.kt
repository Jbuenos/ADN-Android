package com.jomibusa.domain.register.exception

class ExistSameVehicleException :
    RuntimeException("Actualmente ya se encuentra un registro para esa placa")