package by.gmail.vehicle.domain.usecase.student

import by.gmail.vehicle.domain.entity.student.CreateStudent

interface CreateStudentUseCase {

    fun create(student: CreateStudent) : Boolean
}