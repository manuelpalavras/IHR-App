package com.example.ihr.api.model

import com.google.gson.annotations.SerializedName

class RouteObject {

    @SerializedName("Nome")
    private lateinit var name : String

    fun getName(): String {
        return name
    }

    constructor(nome : String) {
        name = nome;
    }

}