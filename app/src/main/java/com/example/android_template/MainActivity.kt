package com.example.android_template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_template.databinding.ActivityMainBinding

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

        LocationKey ="353412"
        var ApiUrl = "https://dataservice.accuweather.com/currentconditions/v1/$LocationKey?apikey=$ApiKey&details=true"

        fetchWeatherData(ApiUrl) { currentConditions ->
            runOnUiThread {
                mList.addAll(listOf(Data(DataType.CURRENT_CONDITION, currentConditions)))
                val adapter = MainAdapter(mList)
                binding.mainRecyclerView.adapter = adapter
            }
        }


    }

}
