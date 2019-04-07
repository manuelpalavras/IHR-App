package com.example.ihr.api.service

import android.graphics.Picture
import android.media.Image
import com.example.ihr.api.model.Route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RoutesClient {

    @GET("/routes")
    fun getAllRoutes(): Call<List<RouteObject>>

    @GET("/route/{routeId}")
    fun getRouteById(@Path("routeId") routeID: String): Call<RouteObject>

    @GET("/route/image/{imageName}")
    fun getImageOfRoute(@Path("imageName") ImageName: String?): Call<String>








}