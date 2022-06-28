package com.jomibusa.domain.entity

import com.jomibusa.domain.valueObject.ICostByParking
import com.jomibusa.domain.valueObject.Plate

abstract class Vehicle(val plate: Plate) : ICostByParking
