package by.gmail.vehicle.presentation.screen.vehicle.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import by.gmail.vehicle.domain.entity.vehicle.Vehicle
import by.gmail.vehicle.presentation.base.BaseMvvmFragment
import by.gmail.vehicle.presentation.screen.vehicle.adapter.VehicleAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_vehicle_list.*


class VehicleListFragment : BaseMvvmFragment<VehicleListViewModel>() {


    companion object {
        fun getInstance() = VehicleListFragment()
    }

    private var vehicleStateDisposable: Disposable? = null

    override fun provideLayoutId() = by.gmail.vehicle.R.layout.fragment_vehicle_list

    override fun provideViewModel(): VehicleListViewModel {
        return ViewModelProviders.of(activity!!,
            VehicleListViewModelFactory()
        )
            .get(VehicleListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val behavior = BottomSheetBehavior.from(bottomSheetLayout)
        view.post {
            behavior.peekHeight = resources
                .getDimensionPixelSize(by.gmail.vehicle.R.dimen.vehicle_bottom_sheet_peek)
            behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }


    }

    override fun initView() {
        observeVehicleState()


       }

//    private fun observeVehicleState() {
//        vehicleStateDisposable = viewModel
//            .vehicleState
//            .subscribe { state ->
//                processState(state)
//            }
//    }

    private fun observeVehicleState() {
            viewModel
            .vehicleState
            .observe(this,
                Observer<VehicleState> { state ->
                    if(state != null) {
                        processState(state)
                    }
                })
    }

    private fun processState(state: VehicleState) {

        when (state) {
            is VehicleState.Progress -> {
                // показываем прогресс
            }
            is VehicleState.Done -> {
                val list = state.vehicleList
                Toast.makeText(
                    context, state.vehicleList.size.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                recyclerView.adapter =
                    VehicleAdapter(list) { vehicle: Vehicle ->
                        partItemClicked(vehicle)
                    }
            }
            is VehicleState.Error -> {
                val error = state.throwable
                Toast.makeText(
                    context, error.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun partItemClicked(vehicleItem : Vehicle) {
        (activity as VehicleMap).setMarker(vehicleItem)
    }

    override fun onDestroy() {
        super.onDestroy()
        vehicleStateDisposable?.dispose()
    }
}