package com.example.ihr.api.model.route

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CoordinateObject() : Parcelable {

    @SerializedName("type")
    private lateinit var type : String

    fun getType () : String {
        return type
    }

    fun setType( newType : String) {
        type = newType
    }


    @SerializedName("coordinates")
    private lateinit var coordinates : List<Any>



    fun getCoordinates() : List<Any> {
        return coordinates
    }

    fun setCoordinates(newCoordinates : List<Any>) {
        coordinates = newCoordinates
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeList(coordinates)

    }

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()
        coordinates = parcel.readArrayList(Double.javaClass.classLoader)

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