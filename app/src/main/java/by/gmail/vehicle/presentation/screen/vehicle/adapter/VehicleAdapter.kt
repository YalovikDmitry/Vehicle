package by.gmail.vehicle.presentation.screen.vehicle.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.gmail.vehicle.domain.entity.vehicle.Vehicle

class VehicleAdapter(private val listVehicle: List<Vehicle>,
                     private val clickListener: (Vehicle) -> Unit) : RecyclerView.Adapter<VehicleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): VehicleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return VehicleViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = listVehicle.size

    override fun onBindViewHolder(holdder: VehicleViewHolder, position: Int) {
        holdder.bind(listVehicle[position], clickListener)
    }

}