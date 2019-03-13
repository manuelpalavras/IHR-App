package com.example.exampleapi.api.service

import com.example.exampleapi.api.model.RouteObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface RoutesClient {

    @GET("/routes")
    fun getAllRoutes() : Call<List<RouteObject>>

    @GET("/route/{routeId}")
    fun getRouteById(@Path("routeId") routeID : String) : Call<RouteObject>




}