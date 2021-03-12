package com.softtek.citi.presentation.ui.home.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.softtek.citi.R
import com.softtek.citi.withArgs
import kotlinx.android.synthetic.main.maps_bottom_sheet_layout.*


class MapsDialog : BottomSheetDialogFragment(), LocationListener {
    private lateinit var locationManager: LocationManager

    private var latitud: Double = 0.0
    private var longitud: Double = 0.0

    private val locationPermissionCode = 2

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(this.latitud, this.longitud)
        googleMap.addMarker(MarkerOptions().position(sydney))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                sydney, 14.0f
            )
        )


    }

    companion object {
        private const val LONGITUD_ARG = "longitud"
        private const val LATITUD_ARG = "latitud"

        fun newInstance(latitud: Double, longitud: Double) =
            MapsDialog().withArgs {
                putDouble(LONGITUD_ARG, latitud)
                putDouble(LATITUD_ARG, longitud)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.let {
            this.latitud = it.getDouble(LONGITUD_ARG, 0.0)
            this.longitud = it.getDouble(LATITUD_ARG, 0.0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.maps_bottom_sheet_layout, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        this.initButtons()
    }

    private fun initButtons() {
        this.btnLocation.setOnClickListener {
            this.getLocation()
            this.tvGpsLocation.visibility = View.VISIBLE
            this.tvGpsLocation.text = context?.getString(R.string.maps_text_get_location)
            this.btnLocation.visibility = View.INVISIBLE
            this.loading.visibility = View.VISIBLE
        }
    }

    private fun getLocation() {
        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }


    @SuppressLint("SetTextI18n")
    override fun onLocationChanged(location: Location) {
        this.loading.visibility = View.GONE
        this.btnLocation.visibility = View.VISIBLE
        this.tvGpsLocation.text =
            "Latitude: ${location.latitude}, Longitude:  ${location.longitude}"
        this.latitud = location.latitude
        this.longitud = location.longitude
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

}