package by.gmail.vehicle.domain.usecase.student

import by.gmail.vehicle.domain.entity.student.Student

interface GetStudentById {

    fun get(id: String) : Student
}