package com.example.project1.api

import com.example.project1.WeatherItem
import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("weather")
    lateinit var weatherItems: List<WeatherItem>
}