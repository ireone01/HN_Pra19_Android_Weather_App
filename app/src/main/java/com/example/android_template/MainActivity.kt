package com.example.android_template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_template.databinding.ActivityMainBinding
import kotlinx.coroutines.*
class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var mList :ArrayList<Data>
    private val ApiKey ="SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy"
    private lateinit var LocationKey : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager =LinearLayoutManager(this)
        mList = ArrayList()


        prepareData()
        val adapter =MainAdapter(mList)
        binding.mainRecyclerView.adapter =adapter

//        LocationKey ="353412"
//        var ApiUrl = "https://dataservice.accuweather.com/currentconditions/v1/$LocationKey?apikey=$ApiKey&details=true"
//        var ApiSunMoon ="https://dataservice.accuweather.com/forecasts/v1/daily/1day/$LocationKey?apikey=$ApiKey&details=true"
//
//        CoroutineScope(Dispatchers.Main).launch {
//            val currentCondition =async { fetchWeatherData(ApiUrl) }
//            val sunMoon  = async { fetSunMoon(ApiSunMoon) }
//            currentCondition.await().let {
//                mList.add(Data.CurrentConditionData(it))
//            }
//            sunMoon.await().let {
//                mList.add(Data.SunMoonData(it))
//            }
//            val adapter = MainAdapter(mList)
//            binding.mainRecyclerView.adapter = adapter
//        }

    }
    private fun prepareData(){
        var currentcondition  = ArrayList<CurrentCondition>()
        currentcondition.add(CurrentCondition("Temperature", "28.9", "C"))
        currentcondition.add(CurrentCondition("RealFeel", "33.6", "C"))
        currentcondition.add(CurrentCondition("Wind", "12.8", "km/h"))
        currentcondition.add(CurrentCondition("Wind Gust", "21.4", "km/h"))
        currentcondition.add(CurrentCondition("Humidity", "80", "%"))
        currentcondition.add(CurrentCondition("Indoor Humidity", "80", "%"))

        var sunmoon = ArrayList<SunMoon>()
        sunmoon.add(SunMoon("Sun","2024-07-25T05:28:00+07:00","2024-07-25T18:39:00+07:00"))
        sunmoon.add(SunMoon("moon","2024-07-25T21:45:00+07:00","2024-07-26T10:11:00+07:00"))
        sunmoon.add(SunMoon("Sun","2024-07-25T05:28:00+07:00","2024-07-25T18:39:00+07:00"))
        sunmoon.add(SunMoon("moon","2024-07-25T21:45:00+07:00","2024-07-26T10:11:00+07:00"))
        sunmoon.add(SunMoon("Sun","2024-07-25T05:28:00+07:00","2024-07-25T18:39:00+07:00",))
        mList.add( Data.CurrentConditionData(currentcondition))
        mList.add(Data.SunMoonData(sunmoon))
    }
}
