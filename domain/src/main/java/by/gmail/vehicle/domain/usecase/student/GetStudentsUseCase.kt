package by.gmail.vehicle.domain.usecase.student

import by.gmail.vehicle.domain.entity.student.Student

interface GetStudentsUseCase {

    fun get() : List<Student>
}