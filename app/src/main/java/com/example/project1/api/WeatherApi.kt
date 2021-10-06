package com.example.project1.api

import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {

    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=yourApiKeyHere" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    fun fetchPhotos(): Call<WeatherFlickrResponse>
}