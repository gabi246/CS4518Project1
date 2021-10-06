package com.example.project1

import WeatherFetchr
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    val weatherItemLiveData: LiveData<List<WeatherItem>>

    init {
        weatherItemLiveData = WeatherFetchr().fetchPhotos()
    }
}