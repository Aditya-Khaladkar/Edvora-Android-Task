package com.example.edvoratask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edvoratask.R
import com.example.edvoratask.model.RideModel

class RideAdapter(val rideList: List<RideModel>) : RecyclerView.Adapter<RideAdapter.RideViewHolder>() {
    class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName = itemView.findViewById<TextView>(R.id.cityName)
        val stateName = itemView.findViewById<TextView>(R.id.stateName)
        val rideId = itemView.findViewById<TextView>(R.id.rideId)
        val originStation = itemView.findViewById<TextView>(R.id.originStation)
        val stationPath = itemView.findViewById<TextView>(R.id.stationPath)
        val date = itemView.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ride_list, parent, false)
        return RideViewHolder(view)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val item = rideList[position]
        holder.cityName.text = item.cityName
        holder.stateName.text = item.stateName
        holder.rideId.text = "Ride Id: ${item.rideId}"
        holder.originStation.text = "Origin Station: ${item.originStation}"
        holder.stationPath.text = "station_path: ${item.stationPath}"
        holder.date.text = "Date: ${item.date}"
    }

    override fun getItemCount(): Int {
        return rideList.size
    }
}