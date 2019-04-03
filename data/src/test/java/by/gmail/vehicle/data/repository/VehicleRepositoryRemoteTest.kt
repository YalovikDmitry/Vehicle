package by.gmail.vehicle.data.repository

import by.gmail.vehicle.data.repository.vehicle.VehicleRepositoryRemote
import by.gmail.vehicle.domain.entity.vehicle.CoordinateParam
import org.junit.Test

class VehicleRepositoryRemoteTest {

    @Test
    fun testRealRequestComplete() {
        val url = "http://kiparo.ru/t/fake-api/car-service.php"
        val repository = VehicleRepositoryRemote(url)

        val coordinateParam = CoordinateParam(
            lat1 = 2.25,
            lon1 = 5.145,
            lat2 = 5.544,
            lon2 = 2.345)

        repository
            .fetch(coordinateParam)
            .test()
            .assertComplete()
    }
}