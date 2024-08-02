package com.example.android_template.Fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.android_template.Api
import com.example.android_template.R
import com.example.android_template.databinding.FragmentAppBarBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

class AppBarFragment : Fragment() {
    private var _binding: FragmentAppBarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppBarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchWeatherData("Cao Bằng")

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_new_size ->{
                Log.i("onOptionsItemSelected","mi_new_size")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun fetchWeatherData(city: String) {
        lifecycleScope.launch {
            val apiKey = "j8aiCZItQzDyTe5Oi28vOUTitlkSjXH6"
            val client = OkHttpClient()
            val locationKey = withContext(Dispatchers.IO) {
                val locationUrl = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=$apiKey&q=$city"
                val request = Request.Builder().url(locationUrl).build()
                client.newCall(request).execute().use { response ->
                    val responseData = response.body?.string() ?: return@withContext null
                    println("Location response: $responseData")

                    try {
                        if (responseData.startsWith("[")) {
                            val jsonArray = JSONArray(responseData)
                            if (jsonArray.length() > 0) {
                                jsonArray.getJSONObject(0).getString("Key")
                            } else {
                                null
                            }
                        } else {
                            val jsonObject = JSONObject(responseData)
                            if (jsonObject.has("Code")) {
                                println("Error: ${jsonObject.getString("Message")}")
                                null
                            } else {
                                null
                            }
                        }
                    } catch (e: Exception) {
                        println("Error parsing location response: ${e.message}")
                        null
                    }
                }
            }

            println("Location Key: $locationKey")

            if (locationKey != null) {
                Api.LocationKey = locationKey

                val weatherUrl = "https://dataservice.accuweather.com/currentconditions/v1/$locationKey?apikey=$apiKey&details=true"
                val request = Request.Builder().url(weatherUrl).build()
                val weatherData = withContext(Dispatchers.IO) {
                    client.newCall(request).execute().use { response ->
                        val responseData = response.body?.string() ?: return@withContext null
                        println("Weather response: $responseData")

                        try {
                            if (responseData.startsWith("[")) {
                                val jsonArray = JSONArray(responseData)
                                if (jsonArray.length() > 0) {
                                    val jsonObject = jsonArray.getJSONObject(0)
                                    val weatherText = jsonObject.getString("WeatherText")
                                    val temperature = jsonObject.getJSONObject("Temperature").getJSONObject("Metric").getDouble("Value").toString()
                                    weatherText to temperature
                                } else {
                                    null
                                }
                            } else {
                                val jsonObject = JSONObject(responseData)
                                if (jsonObject.has("Code")) {
                                    println("Error: ${jsonObject.getString("Message")}")
                                    null
                                } else {
                                    null
                                }
                            }
                        } catch (e: Exception) {
                            println("Error parsing weather response: ${e.message}")
                            null
                        }
                    }
                }

                weatherData?.let { (weatherText, temperature) ->
                    println("Weather Text: $weatherText, Temperature: $temperature")
                    binding.tvCityName.text = city
                    binding.tvTemperature.text = "$temperature°C"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}