package com.example.ihr.api.service

import com.example.ihr.api.model.route.RouteObject
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RoutesClient {

    @GET("/routes")
    fun getAllRoutes(): Call<List<RouteObject>>

    @GET("/route/{routeId}")
    fun getRouteById(@Path("routeId") routeID: String): Call<RouteObject>


    @GET("/routes/PoI/{namePoI}")
    fun getRoutesFromPoI(@Path("namePoI") namePoI: String): Call<List<RouteObject>>

    @POST("/user/updateHistory")
    fun postNewRating(@Body json : JsonObject) : Call<Boolean>








}