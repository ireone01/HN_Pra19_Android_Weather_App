package com.example.android_template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_template.databinding.ACurrentConditionBinding
import com.example.android_template.databinding.CurrentConditionBinding

class MainAdapter(private val weather : List<Data>) :
RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    inner class ACurrentConditionHolder(private val binding : ACurrentConditionBinding):
            RecyclerView.ViewHolder(binding.root){
                init {
                    binding.childRecyclerView.setHasFixedSize(true)
                    binding.childRecyclerView.layoutManager=LinearLayoutManager(binding.root.context,
                        RecyclerView.VERTICAL,false)

                }

                fun bindCurrentCondition(recyclerList : List<CurrentCondition>){
                    val adapter = ChildAdapter(DataType.CURRENT_CONDITION,recyclerList)
                    binding.childRecyclerView.adapter =adapter
                }
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
               val binding = ACurrentConditionBinding.inflate(
                   LayoutInflater.from(parent.context),
                   parent,
                   false
               )
        return ACurrentConditionHolder(binding)
    }

    override fun getItemCount(): Int {
        return weather.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
     when(holder){
            is ACurrentConditionHolder ->{
                weather[position].CurrentConditionList?.let { holder.bindCurrentCondition(it) }
            }
     }
    }

}