package by.gmail.vehicle.presentation.test.mvp

class TestPresenterSpec(private val view: TestView) :
    TestPresenter {

    private val helloText: String

    init {
        helloText = "Kdhjksfjhdkjhf"
    }

    override fun onReloadData() {
        view.showHelloText(helloText)
    }

    override fun onHelloClick() {

    }
}