package com.example.android_template.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_template.*
import com.example.android_template.databinding.HomeFragmentBinding
import kotlinx.coroutines.*

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private lateinit var mList: ArrayList<Data>
    private val ApiKey = "SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy"
    private lateinit var LocationKey: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
        mList = ArrayList()

        prepareData()
        val adapter = MainAdapter(mList)
        binding.mainRecyclerView.adapter = adapter
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

    private fun prepareData() {
        val currentcondition = ArrayList<CurrentCondition>()
        currentcondition.add(CurrentCondition("Temperature", "28.9", "C"))
        currentcondition.add(CurrentCondition("RealFeel", "33.6", "C"))
        currentcondition.add(CurrentCondition("Wind", "12.8", "km/h"))
        currentcondition.add(CurrentCondition("Wind Gust", "21.4", "km/h"))
        currentcondition.add(CurrentCondition("Humidity", "80", "%"))
        currentcondition.add(CurrentCondition("Indoor Humidity", "80", "%"))

        val sunmoon = ArrayList<SunMoon>()
        sunmoon.add(SunMoon("Sun", "2024-07-25T05:28:00+07:00", "2024-07-25T18:39:00+07:00"))
        sunmoon.add(SunMoon("moon", "2024-07-25T21:45:00+07:00", "2024-07-26T10:11:00+07:00"))

        mList.add(Data.CurrentConditionData(currentcondition))
        mList.add(Data.SunMoonData(sunmoon))
    }


}
