package com.example.ihr.api.model.Route

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class RouteObject() : Parcelable {


    // ------------------------------

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

    @SerializedName("Avaliacoes")
    private var numberEvalutation : Int = 0

    fun getEvaluation() : Int {
        return numberEvalutation
    }

    fun setEvalution(newNumberEvaluation: Int) {
        numberEvalutation = newNumberEvaluation
    }

    // ------------------------------

    @SerializedName("Dificuldade")
    private  lateinit var difficulty : String

    fun getDifficulty() : String {
        return difficulty
    }

    fun setDifficulty(newDificulty: String) {
        difficulty = newDificulty
    }

    // ------------------------------
    @SerializedName("Duracao")
    private  lateinit var duration : String

    fun getDuration() : String {
        return duration
    }

    fun setDuration(newDuration: String) {
        duration = newDuration
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

    //------------ GENERATED ----------------------------------

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        description = parcel.readString()
        type = parcel.createStringArrayList()
        image = parcel.readString()
        classification = parcel.readDouble()
        handyCap = parcel.readByte() != 0.toByte()
        numberEvalutation = parcel.readInt()
        difficulty = parcel.readString()
        duration = parcel.readString()
        country = parcel.readString()
        city = parcel.readString()
        poi = parcel.createTypedArrayList(PoiObject)
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeStringList(type)
        parcel.writeString(image)
        parcel.writeDouble(classification)
        parcel.writeByte(if (handyCap) 1 else 0)
        parcel.writeInt(numberEvalutation)
        parcel.writeString(difficulty)
        parcel.writeString(duration)
        parcel.writeString(country)
        parcel.writeString(city)
        parcel.writeTypedArray(poi.toTypedArray(),flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RouteObject> {
        override fun createFromParcel(parcel: Parcel): RouteObject {
            return RouteObject(parcel)
        }

        override fun newArray(size: Int): Array<RouteObject?> {
            return arrayOfNulls(size)
        }
    }


}