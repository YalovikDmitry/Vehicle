package by.gmail.vehicle.presentation.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val lazyDisposable: Lazy<CompositeDisposable>
            = lazy(LazyThreadSafetyMode.NONE) {
        CompositeDisposable()
    }
    protected val disposables: CompositeDisposable  by lazyDisposable

    override fun onCleared() {
        super.onCleared()
        if(lazyDisposable.isInitialized()) {
            disposables.dispose()
        }
    }
}