package com.example.android_template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var mList :ArrayList<Data>
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

    }
    private fun prepareData(){
        var currentcondition  = ArrayList<CurrentCondition>()
        currentcondition.add(CurrentCondition("Temperature", "28.9", "C"))
        currentcondition.add(CurrentCondition("RealFeel", "33.6", "C"))
        currentcondition.add(CurrentCondition("Wind", "12.8", "km/h"))
        currentcondition.add(CurrentCondition("Wind Gust", "21.4", "km/h"))
        currentcondition.add(CurrentCondition("Humidity", "80", "%"))
        currentcondition.add(CurrentCondition("Indoor Humidity", "80", "%"))
        mList.add(Data(DataType.CURRENT_CONDITION,currentcondition))
    }
}
