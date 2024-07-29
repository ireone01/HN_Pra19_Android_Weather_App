package com.example.android_template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.android_template.databinding.ACurrentConditionBinding

class HourlyAdapter(private val weather : List<Data>):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){
            inner class ACurrentConditionHolder(private val binding : ACurrentConditionBinding):
                    RecyclerView.ViewHolder(binding.root){

                        fun bindHourlyFragment(hourlyList : List<HourlyFragmentItem>){
                            binding.childRecyclerView.setHasFixedSize(true)
                            binding.childRecyclerView.layoutManager = LinearLayoutManager(binding.root.context,RecyclerView.VERTICAL,false)
                            val adapter = ChildAdapter(DataType.HOURLY_FRAGMENT, HourlyFragmentList = hourlyList)
                            binding.childRecyclerView.adapter = adapter
                            binding.textA.text = "ngay 39-07"
                        }
                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val binding = ACurrentConditionBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,false
       )
        return ACurrentConditionHolder(binding)
    }

    override fun getItemCount(): Int {
      return weather.size
    }

    override fun getItemViewType(position: Int): Int {
        return DataType.HOURLY_FRAGMENT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = weather[position]
        when(data) {
            is Data.HourlyFragmentData -> {
                if(holder is ACurrentConditionHolder){
                    holder.bindHourlyFragment(data.hourlyFragmentList)
                }
            }
            else -> throw IllegalArgumentException("Invalid")
        }
    }
}