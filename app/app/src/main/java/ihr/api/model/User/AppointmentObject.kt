package com.example.exampleapi.api.model.User

import com.google.gson.annotations.SerializedName
import java.util.*

class AppointmentObject {

    @SerializedName("Data")
    private lateinit var date: String

    fun getDate(): String {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }

    // ------------------------------


    @SerializedName("Local")
    private lateinit var place: String

    fun getPlace(): String {
        return place
    }

    fun setPlace(place: String) {
        this.place = place
    }

    // ------------------------------

    @SerializedName("IdUser")
    private lateinit var idUser: String

    fun getIdUser(): String {
        return idUser
    }

    // ------------------------------

    @SerializedName("Estado")
    private lateinit var state: String

    fun getState(): String {
        return state
    }

    fun setState(state: String) {
        this.state = state
    }

}