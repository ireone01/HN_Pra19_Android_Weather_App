package com.example.android_template.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_template.Data
import com.example.android_template.HourlyAdapter
import com.example.android_template.HourlyFragmentItem
import com.example.android_template.R
import com.example.android_template.databinding.ACurrentConditionBinding
import com.example.android_template.databinding.HourFragmentBinding

class HourlyFragment : Fragment(){
    private lateinit var binding: HourFragmentBinding
    private lateinit var mList: ArrayList<Data>
    private val ApiKey = "SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy"
    private lateinit var LocationKey: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HourFragmentBinding.inflate(inflater
            ,container
            ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
        mList = ArrayList()
        prepareData()
        val adapter = HourlyAdapter(mList)
        binding.mainRecyclerView.adapter =adapter
    }
    private fun prepareData(){
        val hourly = ArrayList<HourlyFragmentItem>()
        hourly.add(HourlyFragmentItem("2024-07-25T05:28:00+07:00","23","45","12","3"))
        hourly.add(HourlyFragmentItem("2024-07-25T05:28:00+07:00","23","45","12","3"))
        hourly.add(HourlyFragmentItem("2024-07-25T05:28:00+07:00","23","45","12","3"))
        hourly.add(HourlyFragmentItem("2024-07-25T05:28:00+07:00","23","45","12","3"))
        hourly.add(HourlyFragmentItem("2024-07-25T05:28:00+07:00","23","45","12","3"))
        hourly.add(HourlyFragmentItem("2024-07-25T05:28:00+07:00","23","45","12","3"))
        hourly.add(HourlyFragmentItem("2024-07-25T05:28:00+07:00","23","45","12","3"))
        hourly.add(HourlyFragmentItem("2024-07-25T05:28:00+07:00","23","45","12","3"))
        mList.add(Data.HourlyFragmentData(hourly))

    }
}