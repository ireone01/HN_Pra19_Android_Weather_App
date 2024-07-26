package com.example.android_template

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.android_template.calculateDuration.Companion.calculateHour

import com.example.android_template.databinding.CurrentConditionBinding
import com.example.android_template.databinding.SunMoonBinding

class ChildAdapter(private val ViewType : Int
        , private val CurrentConditionList : List<CurrentCondition> = listOf()
        ,private val SunMoonList : List<SunMoon> = listOf()
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class CurrentConditionHolder(private val binding: CurrentConditionBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bindCurrentCondition(CurrentItem: CurrentCondition){
                  binding.textLabel.text = CurrentItem.Label
                    binding.textValue.text = CurrentItem.Value
                    binding.textUnit.text = CurrentItem.Unit
                }
            }
    inner class SunMoonHolder(private val binding: SunMoonBinding):
            RecyclerView.ViewHolder(binding.root){
                @RequiresApi(Build.VERSION_CODES.O)
                fun bindSunMoon(SunMoonItem : SunMoon){
                    val rise =calculateDuration.extractTime(SunMoonItem.Rise)
                    val set =calculateDuration.extractTime(SunMoonItem.Set)
                    if(SunMoonItem.Sun_or_Moon == "Sun"){
                        binding.imgMoon.setImageResource(R.drawable.sun)

                        binding.tvmoon.text = calculateHour("Sun",rise,set)
                        binding.mtv2moon.text = "Mọc : $rise"
                        binding.mtv3moon.text = "Lặn : $set"
                    }else{
                        binding.imgMoon.setImageResource(R.drawable.moon)
                        binding.tvmoon.text = calculateHour("Moon",rise,set)
                        binding.mtv2moon.text = "Mọc : $rise"
                        binding.mtv3moon.text = "Lặn : $set"
                    }


                }
            }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return   when(viewType) {
           DataType.CURRENT_CONDITION -> {
               val binding = CurrentConditionBinding.inflate(
                   LayoutInflater.from(parent.context),
                   parent,
                   false
               )
               return CurrentConditionHolder(binding)
           }
           DataType.SUN_MOON_TYPE -> {
                      val binding = SunMoonBinding.inflate(
                      LayoutInflater.from(parent.context),
                      parent,
                      false
                  )
                  return SunMoonHolder(binding)
           }
            else ->throw IllegalArgumentException("Invalid view type")
       }
    }

    override fun getItemCount(): Int {
      return when(ViewType){
          DataType.CURRENT_CONDITION ->{
              CurrentConditionList.size
          }
          DataType.SUN_MOON_TYPE ->{
              SunMoonList.size
          }
          else -> throw IllegalArgumentException("Invalid view type")
      }
    }

    override fun getItemViewType(position: Int): Int {
        return ViewType
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CurrentConditionHolder ->{
                holder.bindCurrentCondition(CurrentConditionList[position])
            }
            is SunMoonHolder ->{
                println("Binding SunMoon at position $position: ${SunMoonList[position]}")
                holder.bindSunMoon(SunMoonList[position])
            }
        }

    }


}