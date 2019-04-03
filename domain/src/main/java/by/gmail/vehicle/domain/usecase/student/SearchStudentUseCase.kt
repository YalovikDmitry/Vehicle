package by.gmail.vehicle.domain.usecase.student

import by.gmail.vehicle.domain.entity.student.SearchParams
import by.gmail.vehicle.domain.entity.student.Student

interface SearchStudentUseCase {

    fun search(params: SearchParams) : List<Student>
}