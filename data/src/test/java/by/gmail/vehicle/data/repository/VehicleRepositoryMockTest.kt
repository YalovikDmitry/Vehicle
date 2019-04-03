package by.gmail.vehicle.data.repository

import by.gmail.vehicle.data.repository.vehicle.VehicleRepositoryRemote
import by.gmail.vehicle.domain.entity.vehicle.CoordinateParam
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class VehicleRepositoryMockTest {

    private lateinit var mockServer: MockWebServer
    private lateinit var url: HttpUrl
    private lateinit var repository: VehicleRepositoryRemote
    private lateinit var  coordinateParam: CoordinateParam

    @Before
    @Throws(IOException::class)
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()
        url = mockServer.url("")
        repository = VehicleRepositoryRemote(url.toString())

        coordinateParam = CoordinateParam(
            lat1 = 2.25,
            lon1 = 5.145,
            lat2 = 5.544,
            lon2 = 2.345)
    }

    @After
    fun release() {
        mockServer.shutdown()
    }

    @Test
    fun testNormalResponse() {

        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody(JsonUtil.getJson(JsonUtil.NORMAL_RESPONSE))

        repository
            .fetch(coordinateParam)
            .test()
            .assertValue {
                it.size == 11
            }
    }

    @Test
    fun testErrorResponse() {

        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody(JsonUtil.getJson(JsonUtil.ERROR_RESPONSE))

        repository
            .fetch(coordinateParam)
            .test()
            .assertError {
                true
            }
    }

    @Test
    fun testError2Response() {

        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody(JsonUtil.getJson(JsonUtil.ERROR_RESPONSE))

        repository
            .fetch(coordinateParam)
            .test()
            .assertError{
                true
            }
    }
}