package com.example.ihr.api.model.User

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class AppointmentObject() : Parcelable {

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(place)
        parcel.writeString(idUser)
        parcel.writeString(state)
    }

    constructor(parcel: Parcel) : this() {
        date = parcel.readString()
        place = parcel.readString()
        idUser = parcel.readString()
        state = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppointmentObject> {
        override fun createFromParcel(parcel: Parcel): AppointmentObject {
            return AppointmentObject(parcel)
        }

        override fun newArray(size: Int): Array<AppointmentObject?> {
            return arrayOfNulls(size)
        }
    }

}