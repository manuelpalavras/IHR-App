package com.example.exampleapi.api.service


import com.example.exampleapi.api.model.RouteObject
import com.example.exampleapi.api.model.User.UserObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface UserClient{

    @GET("/user/email={email}")
    fun getUser(@Path("email") email : String) : Call<UserObject>






}