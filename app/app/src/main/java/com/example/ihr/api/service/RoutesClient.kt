package com.example.ihr.api.service

import com.example.ihr.api.model.RouteObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoutesClient {

    @GET("/routes")
    fun getAllRoutes() : Call<List<RouteObject>>

    @GET("/route/{routeId}")
    fun getRouteById(@Path("routeId") routeID : String) : Call<RouteObject>




}