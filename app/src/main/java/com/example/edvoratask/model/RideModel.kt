package com.example.edvoratask.model

data class RideModel(
    val rideId: Int,
    val cityName: String,
    val stateName: String,
    val originStation: String,
    val stationPath: String,
    val date: String,
    val distance: Int
)