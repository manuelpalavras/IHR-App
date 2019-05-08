package com.example.ihr.api.ui.activities


import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.os.Parcelable
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.ihr.R
import com.example.ihr.api.model.route.RouteObject
import com.mapbox.api.directions.v5.DirectionsCriteria
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.services.android.navigation.ui.v5.NavigationView
import com.mapbox.services.android.navigation.ui.v5.NavigationViewOptions
import com.mapbox.services.android.navigation.ui.v5.OnNavigationReadyCallback
import com.mapbox.services.android.navigation.ui.v5.listeners.NavigationListener
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigationOptions
import com.mapbox.services.android.navigation.v5.navigation.NavigationConstants
import com.mapbox.services.android.navigation.v5.routeprogress.ProgressChangeListener
import com.mapbox.services.android.navigation.v5.routeprogress.RouteProgress


class NavigationActivity : AppCompatActivity(), OnNavigationReadyCallback, NavigationListener, ProgressChangeListener {

    private var navigationView: NavigationView? = null
    private var counterPoI = 0
    private var counterDistance = 0.0
    private lateinit var rota: RouteObject

    private lateinit var directionsRoute: DirectionsRoute


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        navigationView = findViewById(R.id.navigationView)
        navigationView!!.onCreate(savedInstanceState)
        rota = intent.extras.getParcelable("rota")

        rota.getPoi().forEach {
            val point = Point.fromLngLat(
                it.getCoordenates().getCoordinates()[1].toString().toDouble(),
                it.getCoordenates().getCoordinates()[0].toString().toDouble()
            )
            navigationView!!.addMarker(point)
        }


        initialize()
    }

    public override fun onStart() {
        super.onStart()
        navigationView!!.onStart()
    }

    public override fun onResume() {
        super.onResume()
        navigationView!!.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        navigationView!!.onLowMemory()
    }

    override fun onBackPressed() {
        // If the navigation view didn't need to do anything, call super
        if (!navigationView!!.onBackPressed()) {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navigationView!!.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        navigationView!!.onRestoreInstanceState(savedInstanceState)
    }

    public override fun onPause() {
        super.onPause()
        navigationView!!.onPause()
    }

    public override fun onStop() {
        super.onStop()
        navigationView!!.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationView!!.onDestroy()
    }



    override fun onNavigationReady(isRunning: Boolean) {
        val options = NavigationViewOptions.builder()
        options.navigationListener(this@NavigationActivity).progressChangeListener(this@NavigationActivity)
        extractRoute(options)
        extractConfiguration(options)
        options.navigationOptions(MapboxNavigationOptions.builder().build())

        navigationView!!.startNavigation(options.build())
    }

    override fun onCancelNavigation() {
        finishNavigation()
    }

    override fun onNavigationFinished() {
        finishNavigation()
    }

    override fun onNavigationRunning() {

    }

    private fun initialize() {
        val position = intent.getParcelableExtra<Parcelable>(NavigationConstants.NAVIGATION_VIEW_INITIAL_MAP_POSITION)
        if (position != null) {
            navigationView!!.initialize(this, position as CameraPosition)
        } else {
            navigationView!!.initialize(this)
        }
    }

    private fun extractRoute(options: NavigationViewOptions.Builder) {
        directionsRoute = NavigationLauncher.extractRoute(this)
        options.directionsRoute(directionsRoute)
    }

    private fun extractConfiguration(options: NavigationViewOptions.Builder) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        options.shouldSimulateRoute(
            preferences
                .getBoolean(NavigationConstants.NAVIGATION_VIEW_SIMULATE_ROUTE, false)
        )
        options.directionsProfile(
            preferences
                .getString(
                    NavigationConstants.NAVIGATION_VIEW_ROUTE_PROFILE_KEY,
                    DirectionsCriteria.PROFILE_DRIVING_TRAFFIC
                )
        )
    }

    private fun finishNavigation() {
        NavigationLauncher.cleanUpPreferences(this)
        finish()
    }

    override fun onProgressChange(location: Location?, routeProgress: RouteProgress?) {

        if (routeProgress!!.legIndex() == counterPoI + 1 && calculateDistance(
                rota.getPoi()[counterPoI].getCoordenates().getCoordinates()[0].toString().toDouble(),
                rota.getPoi()[counterPoI].getCoordenates().getCoordinates()[1].toString().toDouble(),
                location!!.latitude, location.longitude
            ).toDouble() < routeProgress!!.distanceTraveled() + 100
        ) {
            val intent = Intent(this@NavigationActivity, PoiArrival::class.java)
            val bundle = Bundle()
            bundle.putParcelable("poi", rota.getPoi()[counterPoI])
            intent.putExtras(bundle)
            counterPoI++
            if(counterPoI == 0)
            counterDistance += routeProgress.distanceTraveled()
            startActivity(intent)
        }

    }

    private fun circleCalc(xCentre: Double, yCentre: Double, xUser: Double, yUser: Double): Boolean {
        return Math.pow((xUser - xCentre), 2.0) + Math.pow((yUser - yCentre), 2.0) <= calculateDistance(
            xUser,
            yUser,
            xCentre,
            yCentre
        ) + 100
    }

    fun calculateDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Float {
        val results = FloatArray(1)
        Location.distanceBetween(lat1, lng1, lat2, lng2, results)
        // distance in meter
        return results[0]
    }


}