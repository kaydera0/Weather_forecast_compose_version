package com.example.weatherforecast.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.adapters.RecycleWeekdayAdapter
import com.example.weatherforecast.databinding.FragmentCityBinding
import com.example.weatherforecast.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : Fragment() {

    private val vm: MainViewModel by activityViewModels()
    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(layoutInflater)
        val bundle = arguments?.getString("city")
        setBackground(bundle.toString())

        binding.city.text = vm.currentCity.value?.name
        binding.time.text = vm.currentCity.value?.time
        binding.dayName.text = vm.currentCity.value?.day
        binding.temperature.text = vm.currentCity.value?.temperature
        binding.weaterDescription.text = vm.currentCity.value?.weatherDescription
        binding.recycleCityFragment.adapter =
            RecycleWeekdayAdapter(vm.currentCity.value?.weeklyWeatherArrayList!!)
        binding.recycleCityFragment.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    private fun setBackground(city: String) {
        if (city.equals("Rustavi")) {
            binding.cityFragmentContraint.background = resources.getDrawable(R.mipmap.georgia)
        }
        if (city.equals("Karlsruhe")) {
            binding.cityFragmentContraint.background = resources.getDrawable(R.mipmap.karlsruhe)
        }
        if (city.equals("Kelowna")) {
            binding.cityFragmentContraint.background = resources.getDrawable(R.mipmap.kelowna)
        }
        if (city.equals("Mariupol")) {
            binding.cityFragmentContraint.background = resources.getDrawable(R.mipmap.mariupol)
        }
    }
}