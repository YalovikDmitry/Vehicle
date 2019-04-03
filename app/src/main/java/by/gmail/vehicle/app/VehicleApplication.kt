package by.gmail.vehicle.app

import android.app.Application

class VehicleApplication: Application() {
    companion object {
         lateinit var instanse: VehicleApplication
    }

    override fun onCreate() {
        super.onCreate()
        instanse = this
    }
}