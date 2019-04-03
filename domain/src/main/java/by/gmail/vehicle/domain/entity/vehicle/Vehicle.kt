package by.gmail.vehicle.domain.entity.vehicle

import by.gmail.vehicle.domain.entity.DomainEntity

data class Vehicle(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val fleetType: FleetType,
    val heading: Double
) : DomainEntity