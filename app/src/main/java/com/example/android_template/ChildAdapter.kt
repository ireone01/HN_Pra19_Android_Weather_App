package com.example.android_template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.android_template.databinding.CurrentConditionBinding

class ChildAdapter(private val ViewType : Int  , private val CurrentConditionList : List<CurrentCondition>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class CurrentConditionHolder(private val binding: CurrentConditionBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bindCurrentCondition(CurrentItem: CurrentCondition){
                    val Value = "${CurrentItem.Temperature.Metric.Value} + ${CurrentItem.Temperature.Metric.Unit}"
                    binding.textTemperatureValue.text =Value
//                    binding.textRealfeelValue =
//                    binding.textWindValue
//                    binding.textWindgustValue
//                    binding.textHumidityValue
//                    binding.textIndoorHumidityValue
                }
            }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        when(viewType){
//            DataType.CURRENT_CONDITION -> {
                val binding = CurrentConditionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CurrentConditionHolder(binding)
//            }
//        }
    }

    override fun getItemCount(): Int {
      return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}