package com.example.ihr.api.model.user

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class AvailabilityObject() : Parcelable{

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

    constructor(parcel: Parcel) : this() {
        date = parcel.readString()
        city = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(city)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AvailabilityObject> {
        override fun createFromParcel(parcel: Parcel): AvailabilityObject {
            return AvailabilityObject(parcel)
        }

        override fun newArray(size: Int): Array<AvailabilityObject?> {
            return arrayOfNulls(size)
        }
    }

}