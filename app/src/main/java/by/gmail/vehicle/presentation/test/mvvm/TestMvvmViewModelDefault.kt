package by.gmail.vehicle.presentation.test.mvvm

class TestMvvmViewModelDefault : TestMvvmViewModel {

    private var helloText: String = ""
    private var listener: HelloTextListener? = null

    override fun setHelloTextListener(listener: HelloTextListener) {
        this.listener = listener
    }

    override fun reloadData() {
        helloText = "Hello from Mvvm"
        listener?.onUpdated(helloText)
    }

    override fun helloClick() {
    }
}