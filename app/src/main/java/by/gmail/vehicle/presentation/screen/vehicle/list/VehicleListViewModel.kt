package by.gmail.vehicle.presentation.screen.vehicle.list

import android.arch.lifecycle.MutableLiveData
import by.gmail.vehicle.domain.entity.AppException
import by.gmail.vehicle.domain.entity.vehicle.Vehicle
import by.gmail.vehicle.domain.usecase.vehicle.GetVehicleUseCase
import by.gmail.vehicle.presentation.base.BaseViewModel

class VehicleListViewModel(vehicleUseCase: GetVehicleUseCase) : BaseViewModel() {

//    val vehicleState = PublishSubject.create<VehicleState>()
    val vehicleState = MutableLiveData<VehicleState>()

    val vehicleClicked = MutableLiveData<Vehicle>()

    init {

//        vehicleState.onNext(VehicleState.Progress)
        vehicleState.value = VehicleState.Progress

        val disposable = vehicleUseCase
            .get()
            .subscribe({
//                vehicleState.onNext(VehicleState.Done(it))
                vehicleState.value = VehicleState.Done(it)
            }, {

                if( it is AppException){
                    it.type
                }else{

                }
                //vehicleState.onNext(VehicleState.Error(it))
                vehicleState.value = VehicleState.Error(it)
            })
        disposables.add(disposable)
    }

    fun vechicleClick(vehicle: Vehicle) {
        vehicleClicked.value = vehicle
    }
}