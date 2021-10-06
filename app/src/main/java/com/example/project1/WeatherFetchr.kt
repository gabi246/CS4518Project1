import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project1.WeatherItem
import com.example.project1.api.WeatherApi
import com.example.project1.api.WeatherFlickrResponse
import com.example.project1.api.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "WeatherFetchr"

class WeatherFetchr {

    private val weatherApi: WeatherApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    fun fetchPhotos(): LiveData<List<WeatherItem>> {
        val responseLiveData: MutableLiveData<List<WeatherItem>> = MutableLiveData()
        val weatherRequest: Call<WeatherFlickrResponse> = weatherApi.fetchPhotos()

        weatherRequest.enqueue(object : Callback<WeatherFlickrResponse> {
            override fun onFailure(call: Call<WeatherFlickrResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }
            override fun onResponse(
                call: Call<WeatherFlickrResponse>,
                response: Response<WeatherFlickrResponse>
            ) {
                Log.d(TAG, "Response received")
                val weatherFlickrResponse: WeatherFlickrResponse? = response.body()
                val weatherResponse: WeatherResponse? = weatherFlickrResponse?.weathers
                var weatherItems: List<WeatherItem> = weatherResponse?.weatherItems
                    ?: mutableListOf()
                weatherItems = weatherItems.filterNot {
                    it.url.isBlank()
                }
                responseLiveData.value = weatherItems
            }
        })
        return responseLiveData
    }
}