package com.example.exampleapi.api.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class RouteObject {

    @SerializedName("Nome")
    private lateinit var name : String

    fun getName(): String {
        return name
    }

}