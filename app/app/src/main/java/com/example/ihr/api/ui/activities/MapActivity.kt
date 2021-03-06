package com.example.ihr.api.ui.activities

import com.example.ihr.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.ihr.BuildConfig
import com.example.ihr.api.model.route.RouteObject
import com.example.ihr.api.model.routeprogress.RouteProgressObject
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineListener
import com.mapbox.android.core.location.LocationEnginePriority
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.api.directions.v5.DirectionsCriteria
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions

import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import kotlinx.android.synthetic.main.activity_map.*


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapActivity : AppCompatActivity(),
    PermissionsListener, LocationEngineListener, OnMapReadyCallback, MapboxMap.OnMarkerClickListener {

    //1
    val REQUEST_CHECK_SETTINGS = 1
    var settingsClient: SettingsClient? = null
    private val TAG = "MapActivity"

    //2

    private lateinit var map: MapboxMap
    private lateinit var permissionManager: PermissionsManager
    private var originLocation: Location? = null
    private var locationEngine: LocationEngine? = null
    private var locationComponent: LocationComponent? = null
    private var currentRoute: DirectionsRoute? = null


    private lateinit var rota: RouteObject
    private var startNavigation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, BuildConfig.API_KEY)
        setContentView(R.layout.activity_map)
        mapbox.onCreate(savedInstanceState)
        mapbox.getMapAsync(this)
        settingsClient = LocationServices.getSettingsClient(this)
        btnNavigate.isEnabled = false
        rota = intent.extras.getParcelable("rota")
        btnNavigate.setOnClickListener {
            startNavigation()
        }

        getRoutes(rota)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                enableLocation()
            } else
                if (resultCode == Activity.RESULT_CANCELED) {
                    finish()
                }
        }
    }

    @SuppressWarnings("MissingPermission")
    override fun onStart() {
        super.onStart()
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            locationEngine?.requestLocationUpdates()
            locationComponent?.onStart()
        }

        mapbox.onStart()
        getRoutes(rota)
    }

    override fun onResume() {
        super.onResume()
        mapbox.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapbox.onPause()
    }

    override fun onStop() {
        super.onStop()
        locationEngine?.removeLocationUpdates()
        locationComponent?.onStop()
        mapbox.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationEngine?.deactivate()
        mapbox.onDestroy()

    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapbox.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            mapbox.onSaveInstanceState(outState)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(
            this,
            "This app needs location permission to be able to show your location on the map",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            enableLocation()
        } else {
            Toast.makeText(this, "User location was not granted", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onLocationChanged(location: Location?) {
        location?.run {
            originLocation = this
            setCameraPosition(this)
        }
    }

    @SuppressWarnings("MissingPermission")
    override fun onConnected() {
        locationEngine?.requestLocationUpdates()
    }

    override fun onMapReady(mapboxMap: MapboxMap?) {
        //1
        map = mapboxMap ?: return
        //2
        map.setStyle(getString(R.string.navigation_guidance_day))

        val locationRequestBuilder = LocationSettingsRequest.Builder().addLocationRequest(
            LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        )
        //3
        val locationRequest = locationRequestBuilder?.build()

        settingsClient?.checkLocationSettings(locationRequest)?.run {
            addOnSuccessListener {
                enableLocation()
            }

            addOnFailureListener {
                val statusCode = (it as ApiException).statusCode

                if (statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                    val resolvableException = it as? ResolvableApiException
                    resolvableException?.startResolutionForResult(this@MapActivity, REQUEST_CHECK_SETTINGS)
                }
            }
        }


    }

    //1
    private fun enableLocation() {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            initializeLocationComponent()
            initializeLocationEngine()
        } else {
            permissionManager = PermissionsManager(this)
            permissionManager.requestLocationPermissions(this)
        }
    }

    //2
    @SuppressWarnings("MissingPermission")
    fun initializeLocationEngine() {
        locationEngine = LocationEngineProvider(this@MapActivity).obtainBestLocationEngineAvailable()
        locationEngine?.priority = LocationEnginePriority.HIGH_ACCURACY
        locationEngine?.activate()
        locationEngine?.addLocationEngineListener(this)


        val lastLocation = locationEngine?.lastLocation
        if (lastLocation != null) {
            originLocation = lastLocation
            setCameraPosition(lastLocation)

        } else {

            locationEngine?.addLocationEngineListener(this)
        }
        rota.getPoi().forEach {
            val point = LatLng(
                it.getCoordenates().getCoordinates()[0].toString().toDouble(),
                it.getCoordenates().getCoordinates()[1].toString().toDouble()
            )
            map.addMarker(
                MarkerOptions()
                    .title(it.getName())
                    .snippet(it.getDescription())
                    .position(
                        point
                    )
            )
            map.setOnMarkerClickListener(this)
        }
        getRoutes(rota)
        checkLocation()



    }


    @SuppressWarnings("MissingPermission")
    fun initializeLocationComponent() {
        locationComponent = map.locationComponent
        locationComponent?.activateLocationComponent(this)
        locationComponent?.isLocationComponentEnabled = true
        locationComponent?.cameraMode = CameraMode.TRACKING
    }

    //3
    private fun setCameraPosition(location: Location) {
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    location.latitude,
                    location.longitude
                ), map.cameraPosition.zoom + 100
            )
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("MissingPermission")
    private fun checkLocation() {
        if (originLocation == null) {
            map.locationComponent.lastKnownLocation?.run {
                originLocation = this
            }
        }
    }

    private fun getRoutes(rota: RouteObject) {

        val originPoint = try {
            Point.fromLngLat(
                -9.1527106,
                38.7070497)
            //Point.fromLngLat(originLocation!!.longitude, originLocation!!.latitude)

        } catch (e: Exception) {

            Point.fromLngLat(
                rota.getPoi()[0].getCoordenates().getCoordinates()[1].toString().toDouble(),
                rota.getPoi()[0].getCoordenates().getCoordinates()[0].toString().toDouble()
            )
        }

        val builder = NavigationRoute.builder(this)
            .profile(DirectionsCriteria.PROFILE_WALKING)//1
            .accessToken(BuildConfig.API_KEY) //2
            .origin(originPoint)
            .destination(
                Point.fromLngLat(
                    rota.getPoi()[rota.getPoi().size - 1].getCoordenates().getCoordinates()[1].toString().toDouble(),
                    rota.getPoi()[rota.getPoi().size - 1].getCoordenates().getCoordinates()[0].toString().toDouble()
                )
            )

        if (originPoint.latitude() == rota.getPoi()[0].getCoordenates().getCoordinates()[0].toString().toDouble() &&
            originPoint.longitude() == rota.getPoi()[0].getCoordenates().getCoordinates()[1].toString().toDouble()
        ) {

            for (i in 1 until rota.getPoi().size - 1) {
                builder.addWaypoint(
                    Point.fromLngLat(
                        rota.getPoi()[i].getCoordenates().getCoordinates()[1].toString().toDouble(),
                        rota.getPoi()[i].getCoordenates().getCoordinates()[0].toString().toDouble()
                    )
                )
            }
        } else {
            for (i in 0 until rota.getPoi().size - 1) {
                builder.addWaypoint(
                    Point.fromLngLat(
                        rota.getPoi()[i].getCoordenates().getCoordinates()[1].toString().toDouble(),
                        rota.getPoi()[i].getCoordenates().getCoordinates()[0].toString().toDouble()
                    )
                )

            }
        }


        builder.build().getRoute(
            object : Callback<DirectionsResponse> { //6
                override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DirectionsResponse>,
                    response: Response<DirectionsResponse>
                ) {

                    currentRoute = response.body()?.routes()?.first()

                    btnNavigate.isEnabled = true

                    if (startNavigation) {
                        val navigationLauncherOptions = NavigationLauncherOptions.builder() //1
                            .directionsRoute(currentRoute) //2
                            .shouldSimulateRoute(true) //3
                            .build()


                        //4

                        NavigationLauncher.startNavigation(
                            rota,
                            this@MapActivity,
                            navigationLauncherOptions,
                            originLocation
                        )
                    }

                }
            })

    }


    //---------------------------------


    private fun startNavigation() {
        startNavigation = true
        getRoutes(rota)
    }

    override fun onMarkerClick(marker: Marker): Boolean {

//        rota.getPoi().forEach {
//            if (it.getName() == marker.title)
//
//
//
//        }
        return true
    }


}



