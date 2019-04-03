package by.gmail.vehicle.domain.usecase.vehicle

import by.gmail.vehicle.domain.entity.vehicle.Vehicle
import io.reactivex.Single

interface GetVehicleUseCase {

    fun get() : Single<List<Vehicle>>
}