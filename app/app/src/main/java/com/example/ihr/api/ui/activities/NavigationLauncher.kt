package com.example.ihr.api.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat.startActivity
import com.example.ihr.api.model.route.RouteObject
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions
import com.mapbox.services.android.navigation.v5.navigation.NavigationConstants

class NavigationLauncher {

    companion object {

        fun startNavigation(rota : RouteObject, activity: Activity, options: NavigationLauncherOptions) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
            val editor = preferences.edit()

            storeDirectionsRouteValue(options, editor)
            storeConfiguration(options, editor)

            storeThemePreferences(options, editor)

            editor.apply()

            val navigationActivity = Intent(activity, NavigationActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("rota", rota)
            navigationActivity.putExtras(bundle)
            storeInitialMapPosition(options, navigationActivity)
            activity.baseContext.startActivity(navigationActivity)
        }

        fun extractRoute(context: Context): DirectionsRoute {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val directionsRouteJson = preferences.getString(NavigationConstants.NAVIGATION_VIEW_ROUTE_KEY, "")
            return DirectionsRoute.fromJson(directionsRouteJson)
        }

        fun cleanUpPreferences(context: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()
            editor
                .remove(NavigationConstants.NAVIGATION_VIEW_ROUTE_KEY)
                .remove(NavigationConstants.NAVIGATION_VIEW_SIMULATE_ROUTE)
                .remove(NavigationConstants.NAVIGATION_VIEW_ROUTE_PROFILE_KEY)
                .remove(NavigationConstants.NAVIGATION_VIEW_PREFERENCE_SET_THEME)
                .remove(NavigationConstants.NAVIGATION_VIEW_PREFERENCE_SET_THEME)
                .remove(NavigationConstants.NAVIGATION_VIEW_LIGHT_THEME)
                .remove(NavigationConstants.NAVIGATION_VIEW_DARK_THEME)
                .apply()
        }

        private fun storeDirectionsRouteValue(options: NavigationLauncherOptions, editor: SharedPreferences.Editor) {
            editor.putString(NavigationConstants.NAVIGATION_VIEW_ROUTE_KEY, options.directionsRoute().toJson())
        }

        private fun storeConfiguration(options: NavigationLauncherOptions, editor: SharedPreferences.Editor) {
            editor.putBoolean(NavigationConstants.NAVIGATION_VIEW_SIMULATE_ROUTE, options.shouldSimulateRoute())
            editor.putString(NavigationConstants.NAVIGATION_VIEW_ROUTE_PROFILE_KEY, options.directionsProfile())
        }

        private fun storeThemePreferences(options: NavigationLauncherOptions, editor: SharedPreferences.Editor) {
            val preferenceThemeSet = options.lightThemeResId() != null || options.darkThemeResId() != null
            editor.putBoolean(NavigationConstants.NAVIGATION_VIEW_PREFERENCE_SET_THEME, preferenceThemeSet)

            if (preferenceThemeSet) {
                if (options.lightThemeResId() != null) {
                    editor.putInt(NavigationConstants.NAVIGATION_VIEW_LIGHT_THEME, options.lightThemeResId()!!)
                }
                if (options.darkThemeResId() != null) {
                    editor.putInt(NavigationConstants.NAVIGATION_VIEW_DARK_THEME, options.darkThemeResId()!!)
                }
            }
        }

        private fun storeInitialMapPosition(options: NavigationLauncherOptions, navigationActivity: Intent) {
            if (options.initialMapCameraPosition() != null) {
                navigationActivity.putExtra(
                    NavigationConstants.NAVIGATION_VIEW_INITIAL_MAP_POSITION, options.initialMapCameraPosition()
                )
            }
        }
    }


}


