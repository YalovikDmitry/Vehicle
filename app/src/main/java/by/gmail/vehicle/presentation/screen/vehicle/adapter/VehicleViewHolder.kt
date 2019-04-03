package by.gmail.vehicle.presentation.screen.vehicle.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import by.gmail.vehicle.R
import by.gmail.vehicle.domain.entity.vehicle.Vehicle
import kotlinx.android.synthetic.main.map_list_item.view.*

class VehicleViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.map_list_item, parent, false)){


    internal fun bind(item: Vehicle, clickListener: (Vehicle) -> Unit ) {

        itemView.fleetType.text = item.fleetType.toString()
        itemView.heading.text = item.heading.toString()
        itemView.setOnClickListener{clickListener(item)}
        itemView.setOnTouchListener(object : View.OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN-> itemView.setBackgroundColor(Color.WHITE) //Do Something
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
    }

}