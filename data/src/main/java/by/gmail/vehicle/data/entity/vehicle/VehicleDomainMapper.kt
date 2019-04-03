package by.gmail.vehicle.data.entity.vehicle

import by.gmail.vehicle.domain.entity.vehicle.FleetType
import by.gmail.vehicle.domain.entity.vehicle.Vehicle

private const val FLEET_TYPE_TAXI = "TAXI"
private const val FLEET_TYPE_POOLING = "POOLING"

internal fun VehicleResponse.transform(): Vehicle {
    return Vehicle(
        id = id,
        lat = coordinate.latitude,
        lon = coordinate.longitude,
        fleetType = transformFleetType(fleetType),
        heading = heading
    )
}

private fun transformFleetType(fleetType: String) : FleetType {
    return when(fleetType) {
        FLEET_TYPE_TAXI -> FleetType.TAXI
        FLEET_TYPE_POOLING -> FleetType.POOLING
        else -> FleetType.UNDEFINED
    }
}