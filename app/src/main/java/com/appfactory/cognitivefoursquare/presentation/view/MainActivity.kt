package com.appfactory.cognitivefoursquare.presentation.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appfactory.Utils.GpsUtils
import com.appfactory.Utils.SharedPreferencesHelper
import com.appfactory.cognitivefoursquare.R
import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import com.appfactory.cognitivefoursquare.presentation.model.LocationModel
import com.appfactory.cognitivefoursquare.presentation.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var isGPSEnabled = false

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this)[MainActivityViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferencesHelper = SharedPreferencesHelper()

        GpsUtils(this).turnGPSOn(object : GpsUtils.OnGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) {
                this@MainActivity.isGPSEnabled = isGPSEnable
            }
        })




        viewModel.venusObservable.observe(this, Observer {
            if (it.isEmpty()) {
                setErrorView(NO_PLACES_ERROR)
            } else {
                setSuccessLayout(it)
            }
        })

        viewModel.errorObservable.observe(this, Observer {
            setErrorView(GENERAL_ERROR)
        })


        btnRealTimeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sharedPreferencesHelper.isRealtime(this, true)
            } else {
                sharedPreferencesHelper.isRealtime(this, false)

            }
        }
    }


    private fun setErrorView(s: String) {
        toggleVisibility(tvErrorMessage)
        when (s) {
            GENERAL_ERROR -> tvErrorMessage.text = getString(R.string.general_error)
            NETWORK_ERROR -> tvErrorMessage.text = getString(R.string.network_error)
            NO_PLACES_ERROR -> tvErrorMessage.text = getString(R.string.no_data_error)
        }
    }


    private fun setSuccessLayout(it: List<VenueEntity>) {
        val venuesAdapter = VenuesAdapter()
        rvVenues.adapter = venuesAdapter
        rvVenues.layoutManager = LinearLayoutManager(this)
        venuesAdapter.setVenues(it)
        toggleVisibility(rvVenues)
    }

    private fun toggleVisibility(v: View) {
        rvVenues.visibility = View.GONE
        vProgressIndicator.visibility = View.GONE
        tvErrorMessage.visibility = View.GONE
        v.visibility = View.VISIBLE
    }

    private fun getUpdatedVenues(mCurrentLocation: LocationModel) {
        viewModel.getVenues("${mCurrentLocation.latitude},${mCurrentLocation.longitude}")
    }


    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_REQUEST) {
                isGPSEnabled = true
                invokeLocationAction()
            }
        }
    }

    private fun invokeLocationAction() {
        vProgressIndicator.show()
        when {
            !isGPSEnabled -> setErrorView(getString(R.string.enable_gps))

            isPermissionsGranted() -> startLocationUpdate()

            shouldShowRequestPermissionRationale() -> setErrorView(getString(R.string.allow_location))

            else -> ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_REQUEST
            )
        }
    }

    private fun startLocationUpdate() {
        viewModel.getLocationData().observe(this, Observer {
            getUpdatedVenues(it)
        })
    }

    private fun isPermissionsGranted() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowRequestPermissionRationale() =
        ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) && ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST -> {
                invokeLocationAction()
            }
        }
    }


    companion object {
        private const val GENERAL_ERROR = "general_error"
        private const val NETWORK_ERROR = "network_error"
        private const val NO_PLACES_ERROR = "no_places_error"
    }
}


const val LOCATION_REQUEST = 100
const val GPS_REQUEST = 101