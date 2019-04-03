package by.gmail.vehicle.domain.repository.vehicle

import by.gmail.vehicle.domain.entity.vehicle.CoordinateParam
import by.gmail.vehicle.domain.entity.vehicle.Vehicle
import io.reactivex.Single

interface VehicleRepository {

    fun fetch(param:  CoordinateParam) : Single<List<Vehicle>>
}