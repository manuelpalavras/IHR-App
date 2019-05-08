package com.example.ihr.api.model.route

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PoiObject() : Parcelable {

    @SerializedName("Nome")
    private lateinit var name : String

    fun getName(): String {
        return name
    }

    fun setName(newName : String) {
        name = newName
    }

    // ------------------------------

    @SerializedName("Descricao")
    private lateinit var description : String

    fun getDescription(): String {
        return description
    }

    fun setDescription(newDescription : String) {
        description = newDescription
    }

    // ------------------------------

    @SerializedName("Img")
    private lateinit var image : String

    fun getImage() : String {
        return image
    }

    fun setImage(newImage : String) {
        image = newImage
    }

    // ------------------------------

    @SerializedName ("coordenadas")
    private lateinit var coordenates : CoordinateObject


    fun getCoordenates() : CoordinateObject{
        return coordenates
    }

    fun setCoordenates( newCoordenates : CoordinateObject) {
        coordenates = newCoordenates
    }

    // ------------------------------



    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        description = parcel.readString()
        image = parcel.readString()
        coordenates = parcel.readParcelable(CoordinateObject.javaClass.classLoader)

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeParcelable(coordenates,flags)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PoiObject> {
        override fun createFromParcel(parcel: Parcel): PoiObject {
            return PoiObject(parcel)
        }

        override fun newArray(size: Int): Array<PoiObject?> {
            return arrayOfNulls(size)
        }
    }


}