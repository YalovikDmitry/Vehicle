package by.gmail.vehicle.presentation.screen.vehicle.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import by.gmail.vehicle.app.UseCaseProvider

class VehicleListViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VehicleListViewModel(
            UseCaseProvider
                .provideGetVehicleUseCase()
        ) as T
    }
}