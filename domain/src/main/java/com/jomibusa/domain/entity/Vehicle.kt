package com.jomibusa.domain.entity

import com.jomibusa.domain.valueObject.CostByParking
import com.jomibusa.domain.valueObject.Plate

abstract class Vehicle(val plate: Plate) : CostByParking
