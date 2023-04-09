package com.example.weatherforecast.fragments

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.adapters.RecycleDialogAdapter
import com.example.weatherforecast.databinding.BottomSheetDialogLayoutBinding
import com.example.weatherforecast.databinding.FragmentMapBinding
import com.example.weatherforecast.viewModels.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private val vm: MainViewModel by activityViewModels()
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap
    private lateinit var mapSettings: UiSettings
    private lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    private var lastKnownLocation: Location? = null
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(layoutInflater)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        showSheetDialog()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mapSettings = mMap.uiSettings
        mapSettings.isMyLocationButtonEnabled = true

        getDeviceLocation()

    }

    private fun showSheetDialog() {
        vm.cityList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
            val dialog = BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialogTheme)
            val dialogBinding = BottomSheetDialogLayoutBinding.inflate(layoutInflater)
            dialogBinding.recycleDialog.adapter = RecycleDialogAdapter(it, binding.root, dialog,vm)
            dialogBinding.recycleDialog.layoutManager = LinearLayoutManager(requireContext())
            dialogBinding.recycleDialog.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setContentView(dialogBinding.root)
            dialog.show()
        }
    }
        CoroutineScope(Dispatchers.IO).launch {
            vm.updateCityList()
        }
    }
    private fun getDeviceLocation() {
        try {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                LatLng(lastKnownLocation!!.latitude,
                                    lastKnownLocation!!.longitude), 4.toFloat()))
                        }
                    } else {
                        mMap.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(defaultLocation, 4.toFloat()))
                        mMap.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }
}
