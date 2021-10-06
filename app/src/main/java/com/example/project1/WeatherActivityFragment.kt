package com.example.project1

import WeatherFetchr
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.api.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "WeatherActivityFragment"

class WeatherActivityFragment : Fragment() {
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel =
            ViewModelProviders.of(this).get(WeatherViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        weatherRecyclerView = view.findViewById(R.id.weather_recycler_view)
        weatherRecyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.weatherItemLiveData.observe(
            viewLifecycleOwner,
            Observer { weatherItems ->
                weatherRecyclerView.adapter = WeatherAdapter(weatherItems)
            })
    }

    private class WeatherHolder(itemTextView: TextView)
        : RecyclerView.ViewHolder(itemTextView) {

        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class WeatherAdapter(private val weatherItems: List<WeatherItem>)
        : RecyclerView.Adapter<WeatherHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): WeatherHolder {
            val textView = TextView(parent.context)
            return WeatherHolder(textView)
        } override fun getItemCount(): Int = weatherItems.size

        override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
            val weatherItem = weatherItems[position]
            holder.bindTitle(weatherItem.title)
        }
    }

    companion object {
        fun newInstance() = WeatherActivityFragment()
    }
}