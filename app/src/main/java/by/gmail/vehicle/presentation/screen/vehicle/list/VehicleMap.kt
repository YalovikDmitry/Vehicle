package by.gmail.vehicle.presentation.screen.vehicle.list

import by.gmail.vehicle.domain.entity.vehicle.Vehicle

interface VehicleMap {
    fun setMarker(vehicle: Vehicle)
}