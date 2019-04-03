package by.gmail.vehicle.data.repository.vehicle

import android.content.Context
import by.gmail.vehicle.data.BuildConfig
import by.gmail.vehicle.data.db.dao.VehicleDao
import by.gmail.vehicle.data.entity.vehicle.VehiclePoiResponse
import by.gmail.vehicle.data.entity.vehicle.transform
import by.gmail.vehicle.domain.entity.vehicle.CoordinateParam
import by.gmail.vehicle.domain.entity.vehicle.Vehicle
import by.gmail.vehicle.domain.repository.vehicle.VehicleRepository
import by.gmail.vehicle.data.db.dao.AppDatabase
import by.gmail.vehicle.data.entity.vehicle.VehicleResponse
import by.gmail.vehicle.data.net.provideApi
import by.gmail.vehicle.domain.entity.AppException
import by.gmail.vehicle.domain.entity.AppExeptionType
import com.google.gson.Gson
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.Exception
import java.net.SocketTimeoutException

class VehicleRepositoryRemote(context: Context, private val url: String) : VehicleRepository {

    private val vehicleDao: VehicleDao = AppDatabase.create(context).getVehicleDao()

    private val api= provideApi()

    override fun fetch(param: CoordinateParam): Single<List<Vehicle>> {

        val singleListVehicle =
            vehicleDao
                .get()
                .flatMap { it ->
                    if (!it.isNullOrEmpty()) {
                        Single.just(it)
                    } else {
                        remoteRequest(param)
                    }
                }
                .map { vehicleResponce ->
                    vehicleResponce.map { it.transform() }
                }

        return singleListVehicle;
    }


    @Throws(Exception::class)
    private fun requestData(param: CoordinateParam): VehiclePoiResponse {

        val httpUrlBuilder = HttpUrl.parse(url)?.newBuilder()
            ?: throw Exception("Incorrect URL")

        httpUrlBuilder.addQueryParameter("p1Lat", param.lat1.toString())
        httpUrlBuilder.addQueryParameter("p1Lon", param.lon1.toString())
        httpUrlBuilder.addQueryParameter("p2Lat", param.lat2.toString())
        httpUrlBuilder.addQueryParameter("p2Lon", param.lon2.toString())

        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        val okhttp = clientBuilder.build()

        val request = Request.Builder()
            .url(httpUrlBuilder.build())
            .build()

        val response = okhttp.newCall(request).execute()

        val body = response.body()?.string()
            ?: throw Exception("Body is empty")

        val gson = Gson()

        return gson
            .fromJson(body, VehiclePoiResponse::class.java)
    }

    private fun remoteRequest(param: CoordinateParam): Single<List<VehicleResponse>> {
        return Single.create<VehiclePoiResponse> { emitter ->
            try {
                val response = requestData(param)
                vehicleDao.delete()
                vehicleDao.insert(response.poiList)
                emitter.onSuccess(response)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
            .map {
                it.poiList
            }
            .onErrorResumeNext() {
                when (it) {
                    is SocketTimeoutException -> {
                        Single.error(AppException(AppExeptionType.NO_INTERNET))
                    }
                    else -> {
                        Single.error(AppException(AppExeptionType.UNKNOWN))
                    }

                }
            }

    }
}