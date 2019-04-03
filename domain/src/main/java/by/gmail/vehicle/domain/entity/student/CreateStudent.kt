package by.gmail.vehicle.domain.entity.student

import by.gmail.vehicle.domain.entity.DomainEntity

data class CreateStudent(
    val name: String,
    val age: Int,
    val imageUrl: String
) : DomainEntity
