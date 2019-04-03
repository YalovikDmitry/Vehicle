package by.gmail.vehicle.domain.entity.student

import by.gmail.vehicle.domain.entity.DomainEntity

data class Student(
    val id: String,
    val name: String,
    val age: Int,
    val imageUrl: String
) : DomainEntity
