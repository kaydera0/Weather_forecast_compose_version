package com.example.weatherforecast.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.adapters.RecycleDialogAdapter
import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.databinding.BottomSheetDialogLayoutBinding
import com.example.weatherforecast.databinding.FragmentCityBinding
import com.example.weatherforecast.viewModels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : Fragment() {

    private val vm:MainViewModel by viewModels()
    private var _binding:FragmentCityBinding? = null
    private val binding get() = _binding!!
    private val cityList = listOf("Mariupol","Rustavi","Karlsruhe","Mississauga","Kelowna")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(layoutInflater)

        binding.city.text = vm.currentCity?.value?.name
        binding.time.text = vm.currentCity?.value?.time
        binding.dayName.text = vm.currentCity?.value?.day
        binding.temperature.text = vm.currentCity?.value?.temperature
        binding.weaterDescription.text = vm.currentCity?.value?.weatherDescription

        val testList = ArrayList<CityData>()
//        testList.add(CityData("Kelowna","1:00","-3"))
//        testList.add(CityData("Toronto","2:00","0"))
//        binding.recycleCityFragment.adapter =RecycleDialogAdapter(testList,binding.root)
        binding.recycleCityFragment.layoutManager = LinearLayoutManager(requireContext())


        return binding.root
    }
}