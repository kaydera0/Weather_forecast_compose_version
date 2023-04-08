package com.example.weatherforecast.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.adapters.RecycleDialogAdapter
import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.databinding.BottomSheetDialogLayoutBinding
import com.example.weatherforecast.databinding.FragmentMapBinding
import com.example.weatherforecast.viewModels.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private val vm :MainViewModel by viewModels()
    private var _binding:FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap
    private lateinit var mapSettings: UiSettings

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(layoutInflater)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

//        findNavController().navigate(R.id.action_mapFragment_to_cityFragment)

            showSheetDialog()
            vm.getWeater()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mapSettings = mMap.uiSettings
        mapSettings.isMyLocationButtonEnabled = true

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    private fun showSheetDialog(){
        val testList = ArrayList<CityData>()
        testList.add(CityData("Kelowna","1:00","-3","",""))
        testList.add(CityData("Toronto","2:00","0","",""))
        val dialog = BottomSheetDialog(requireContext(),R.style.CustomBottomSheetDialogTheme)
        val dialogBinding = BottomSheetDialogLayoutBinding.inflate(layoutInflater)
        dialogBinding.recycleDialog.adapter = RecycleDialogAdapter(testList,binding.root,dialog)
        dialogBinding.recycleDialog.layoutManager = LinearLayoutManager(requireContext())
        dialogBinding.recycleDialog.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(requireContext(),"123",Toast.LENGTH_SHORT).show()
        }
        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }
}
