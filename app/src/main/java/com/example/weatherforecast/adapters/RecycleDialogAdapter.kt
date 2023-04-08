package com.example.weatherforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.databinding.CityItemSheetDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class RecycleDialogAdapter(val cityList: List<CityData>,val view:View,val dialog:BottomSheetDialog) :
    RecyclerView.Adapter<RecycleDialogAdapter.ViewHolder>() {

    private lateinit var binding: CityItemSheetDialogLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CityItemSheetDialogLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,view,dialog)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    class ViewHolder(val binding: CityItemSheetDialogLayoutBinding,val view:View,val dialog:BottomSheetDialog) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cityData: CityData) {
            binding.cityName.text = cityData.name
            binding.cityTime.text = cityData.time
            binding.cityTemp.text = cityData.temperature
            binding.root.setOnClickListener{

                view.findNavController().navigate(R.id.action_mapFragment_to_cityFragment)
                dialog.dismiss()
            }
        }
    }
}