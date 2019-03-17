package com.example.ihr.api.model.Route

import com.google.gson.annotations.SerializedName

class PoiObject {

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

    @SerializedName ("coordenadas")
    private lateinit var coordenates : CoordinateObject

    fun getCoordenates () : CoordinateObject{
        return coordenates
    }

    fun setCoordenates( newCoordenates : CoordinateObject) {
        coordenates = newCoordenates
    }



}