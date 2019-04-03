package by.gmail.vehicle.app

import by.gmail.vehicle.BuildConfig
import by.gmail.vehicle.data.repository.vehicle.VehicleRepositoryRemote
import by.gmail.vehicle.domain.usecase.vehicle.GetVehicleUseCase
import by.gmail.vehicle.domain.usecase.vehicle.GetVehicleUseCaseHamburg
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object UseCaseProvider {

    fun provideGetVehicleUseCase() : GetVehicleUseCase {
        return GetVehicleUseCaseHamburg(
            workScheduler = getWorkScheduler(),
            postScheduler = getUiScheduler(),
            vehicleRepository = VehicleRepositoryRemote(
                VehicleApplication.instanse.applicationContext,
                BuildConfig.API_ENDPOINT
            )
        )
    }

    private fun getWorkScheduler() = Schedulers.io()

    private fun getUiScheduler() = AndroidSchedulers.mainThread()
}