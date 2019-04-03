package by.gmail.vehicle.presentation.screen.vehicle.list

import by.gmail.vehicle.domain.entity.vehicle.Vehicle

sealed class VehicleState {

    object Progress : VehicleState()
    class Done(val vehicleList: List<Vehicle>) : VehicleState()
    class Error(val throwable: Throwable) : VehicleState()
}