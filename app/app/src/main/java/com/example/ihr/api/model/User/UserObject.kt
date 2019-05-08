package com.example.ihr.api.model.user

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class UserObject() : Parcelable {

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

    fun setBirthDate(birthDate: String) {
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

            appointment.add(newAppointment)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removeAppointment(index: Int): Boolean {

        return try {

            appointment.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }


// ------------------------------

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        email = parcel.readString()
        nacionality = parcel.readString()
        birthDate = parcel.readString()
        guide = parcel.readByte() != 0.toByte()
        favorite = parcel.readParcelable(FavoriteObject.javaClass.classLoader)
        appointment = parcel.createTypedArrayList(AppointmentObject)
        if(guide)
        availability = parcel.createTypedArrayList(AvailabilityObject)

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(nacionality)
        parcel.writeString(birthDate)
        parcel.writeByte(if (guide) 1 else 0)
        parcel.writeParcelable(favorite,flags)
        parcel.writeTypedArray(appointment.toTypedArray(),flags)
        if(guide)
        parcel.writeTypedArray(availability.toTypedArray(),flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserObject> {
        override fun createFromParcel(parcel: Parcel): UserObject {
            return UserObject(parcel)
        }

        override fun newArray(size: Int): Array<UserObject?> {
            return arrayOfNulls(size)
        }
    }


}