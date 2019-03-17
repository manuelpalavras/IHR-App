package com.example.ihr.api.model.Route

import com.google.gson.annotations.SerializedName

class CoordinateObject {

    @SerializedName("tipo")
    private lateinit var type : String

    fun getType () : String {
        return type
    }

    fun setType( newType : String) {
        type = newType
    }


    @SerializedName("coordinates")
    private lateinit var coordinates : List<Double>

    fun getCoordinates() : List<Double> {
        return coordinates
    }

    fun setCoordinates(newCoordinates : List<Double>) {
        coordinates = newCoordinates
    }
}