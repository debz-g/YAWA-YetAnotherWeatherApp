package dev.yawa.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.yawa.weather.databinding.ActivityMainBinding
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mfusedlocation: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    private var reqCode = 1010

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mfusedlocation = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

        binding.tempText.setOnClickListener {
            getLastLocation()
        }

        val swipe: SwipeRefreshLayout =findViewById(R.id.swiperefresh)
        swipe.setOnRefreshListener {
            newLocation()
            swipe.isRefreshing=false
        }
    }

    private fun getLastLocation() {
        if (checkPermission()) {
            if (locationEnable()) {
                mfusedlocation.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        newLocation()
                    }
                    else {
                        Log.i("Location", location.longitude.toString())
                    }
                }
            }
            else {
                Toast.makeText(this, "Please Turn on GPS from settings", Toast.LENGTH_SHORT).show()
            }
        } else
            requestPermission()
    }

    @SuppressLint("MissingPermission")
    private fun newLocation() {
        var locationReq = com.google.android.gms.location.LocationRequest()
        locationReq.priority =
            com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        locationReq.interval = 0
        locationReq.fastestInterval = 0
        locationReq.numUpdates = 1
        mfusedlocation = LocationServices.getFusedLocationProviderClient(this)
        mfusedlocation.requestLocationUpdates(locationReq, locationCallBack, Looper.myLooper())
    }

    private val locationCallBack = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation: Location = p0.lastLocation
        }
    }

    private fun locationEnable(): Boolean {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), reqCode
        )
    }

    private fun checkPermission(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == reqCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            }
        }
    }
}