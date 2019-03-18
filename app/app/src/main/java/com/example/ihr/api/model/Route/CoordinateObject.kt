package com.example.ihr.api.model.Route

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CoordinateObject() : Parcelable {

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)

    }

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoordinateObject> {
        override fun createFromParcel(parcel: Parcel): CoordinateObject {
            return CoordinateObject(parcel)
        }

        override fun newArray(size: Int): Array<CoordinateObject?> {
            return arrayOfNulls(size)
        }
    }
}