package by.gmail.vehicle.presentation.test.mvp

import android.app.Activity
import android.os.Bundle
import by.gmail.vehicle.R
import kotlinx.android.synthetic.main.activity_test_mvp_mvvm.*

class TestMvpActivity : Activity(), TestView {

    lateinit var presenter: TestPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_mvp_mvvm)

        if(true) {
            presenter = TestPresenterDefault(this)
        } else {
            presenter = TestPresenterSpec(this)
        }

        helloTextView.setOnClickListener {
            presenter.onHelloClick()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onReloadData()
    }

    override fun showHelloText(value: String) {
        helloTextView.text = value
    }
}