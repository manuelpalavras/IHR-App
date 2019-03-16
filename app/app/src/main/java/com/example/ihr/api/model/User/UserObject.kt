package com.example.ihr.api.model.User

import com.google.gson.annotations.SerializedName
import ihr.api.model.User.FavoriteObject

class UserObject {

    @SerializedName("Nome")
    private lateinit var name: String

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    // ------------------------------

    @SerializedName("Email")
    private lateinit var email: String

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    // ------------------------------

    @SerializedName("Nacionalidade")
    private lateinit var nacionality: String

    fun getNacionality(): String {
        return nacionality
    }

    fun setNacionality(nacionality: String) {
        this.nacionality = nacionality
    }

    // ------------------------------

    @SerializedName("Data de Nascimento")
    private lateinit var birthDate: String

    fun getBirthDate(): String {
        return birthDate
    }

    fun setBirthDate(birthDate : String) {
        this.birthDate = birthDate
    }

    // ------------------------------

    @SerializedName("Favoritos")
    private lateinit var favorite: FavoriteObject

    fun getFavorite(): FavoriteObject {
        return favorite
    }

    fun setFavorite(newFavoriteList: FavoriteObject) {
        favorite = newFavoriteList
    }



// ------------------------------

    @SerializedName("Guia")
    private var guide: Boolean = false

    fun getGuide(): Boolean {
        return guide
    }

    fun setGuide(newGuide: Boolean) {
        guide = newGuide
    }

// ------------------------------

    @SerializedName("Disponibilidade")
    private var availability: MutableList<AvailabilityObject> = mutableListOf()

    fun getAvailability(): MutableList<AvailabilityObject> {
        return availability
    }

    fun setAvailability(newAvailabilityList: MutableList<AvailabilityObject>) {
        availability = newAvailabilityList
    }

// ------------------------------

    @SerializedName("Marcações")
    private var appointment: MutableList<AppointmentObject> = mutableListOf()

    fun getAppointment(): MutableList<AppointmentObject> {
        return appointment
    }

    fun setAppointment(newAppointmentList: MutableList<AppointmentObject>) {
        appointment = newAppointmentList
    }

    fun addAppointment(newAppointment: AppointmentObject): Boolean {
        return try {

            appointment?.add(newAppointment)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removeAppointment(index: Int): Boolean {

        return try {

            appointment?.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }
}


// ------------------------------


