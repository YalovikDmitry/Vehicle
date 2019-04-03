package by.gmail.vehicle.data.net

import by.gmail.vehicle.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


internal fun provideApi(): ApiRest {

    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)
    }
    val okhttp = clientBuilder.build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://www.kiparo.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okhttp)
        .build()

    return retrofit.create(ApiRest::class.java)
}