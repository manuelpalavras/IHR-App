package com.example.ihr.api.model.Route

import com.google.gson.annotations.SerializedName

class RouteObject {

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

    @SerializedName("PoI")
    private var poi : MutableList<PoiObject> = mutableListOf()

    fun getPoi(): MutableList<PoiObject> {
        return poi
    }

    fun setPoi(newPoI : MutableList<PoiObject>) {
        poi = newPoI
    }

    fun addPoI(newPoI: PoiObject): Boolean {
        return try {

            poi.add(newPoI)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removePoI(index: Int): Boolean {

        return try {

            poi.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }

    // ------------------------------

    @SerializedName("Tipo")
    private lateinit var type : List<String>

    fun getType(): List<String> {
        return type
    }

    fun setType(newType: List<String>) {
        type = newType
    }

    // ------------------------------

    @SerializedName("imagem")
    private lateinit var image : String

    fun getImage() : String {
        return image
    }

    fun setImage(newImage : String) {
        image = newImage
    }

    // ------------------------------

    @SerializedName("Classificacao")
    private var classification : Double = 0.0

    fun getClassification () : Double {
        return classification
    }

    fun setClassification (newClassification : Double) {
        classification = newClassification
    }

    // ------------------------------

    @SerializedName("Deficientes")
    private var handyCap : Boolean = false

    fun getHandyCap() : Boolean {
        return handyCap
    }

    fun setHandyCap(newHandyCap : Boolean) {
        handyCap = newHandyCap
    }

    // ------------------------------

    @SerializedName("Pais")
    private lateinit var country : String

    fun getCountry(): String {
        return country
    }

    fun setCountry(newCountry : String) {
        country = newCountry
    }

    // ------------------------------

    @SerializedName("Cidade")
    private lateinit var city : String

    fun getCity(): String {
        return city
    }

    fun setCity(newCity: String) {
        city = newCity
    }

}