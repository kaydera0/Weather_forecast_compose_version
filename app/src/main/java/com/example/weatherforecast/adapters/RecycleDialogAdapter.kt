package com.example.weatherforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.databinding.CityItemSheetDialogLayoutBinding
import com.example.weatherforecast.viewModels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class RecycleDialogAdapter(
    val cityList: List<CityData>,
    val view: View,
    val dialog: BottomSheetDialog,
    val vm: MainViewModel
) :
    RecyclerView.Adapter<RecycleDialogAdapter.ViewHolder>() {

    private lateinit var binding: CityItemSheetDialogLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CityItemSheetDialogLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, view, dialog, vm)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    class ViewHolder(
        val binding: CityItemSheetDialogLayoutBinding,
        val view: View,
        val dialog: BottomSheetDialog,
        val vm: MainViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cityData: CityData) {
            binding.cityName.text = cityData.name
            binding.cityTime.text = cityData.time
            binding.cityTemp.text = cityData.temperature
            binding.root.setOnClickListener {
                vm.setCurrentCity(cityData)
                val bundle = bundleOf("city" to cityData.name)
                view.findNavController().navigate(R.id.action_mapFragment_to_cityFragment,bundle)
                dialog.dismiss()
            }
        }
    }
}