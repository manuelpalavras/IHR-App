package com.example.ihr.api.service


import com.example.ihr.api.model.user.UserObject
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface UserClient{

    @GET("/user/email={email}")
    fun getUser(@Path("email") email : String) : Call<UserObject>

    @POST("/user/updateHistory")
    fun postHistory(@Body json : JsonObject) : Call<Boolean>




}