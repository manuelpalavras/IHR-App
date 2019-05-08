package com.example.ihr.api.service


import com.example.ihr.api.model.user.UserObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserClient{

    @GET("/user/email={email}")
    fun getUser(@Path("email") email : String) : Call<UserObject>






}