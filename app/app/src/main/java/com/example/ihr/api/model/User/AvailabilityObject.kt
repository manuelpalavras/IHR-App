package com.example.ihr.api.model.User

import com.google.gson.annotations.SerializedName

class AvailabilityObject {

    @SerializedName("Data")
    private lateinit var date: String

    fun getDate(): String {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }

    // ------------------------------


    @SerializedName ("Cidade")
    private lateinit var city: String

    fun getCity(): String {
        return city
    }

    fun setCity(city: String) {
        this.city = city
    }

}