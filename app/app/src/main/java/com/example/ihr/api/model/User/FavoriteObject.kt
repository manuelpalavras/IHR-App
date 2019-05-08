package com.example.ihr.api.model.user

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName



class FavoriteObject() : Parcelable {



    @SerializedName("Rotas")
    private var routes : MutableList<String> = mutableListOf()

    fun getRoute(): MutableList<String> {
        return routes
    }

    fun setRoute(routes: MutableList<String>) {
        this.routes = routes
    }

    fun addRoute(newRoute: String): Boolean {
        return try {

            routes?.add(newRoute)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removeRoute(index: Int): Boolean {

        return try {

            routes?.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }

    // ------------------------------

    @SerializedName("Guias")
    private var guides : MutableList<String> = mutableListOf()

    fun getGuides(): MutableList<String> {
        return guides
    }

    fun setGuides(guides: MutableList<String>) {
        this.guides = guides
    }

    fun addGuide(newGuide: String): Boolean {
        return try {

            guides?.add(newGuide)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removeGuide(index: Int): Boolean {

        return try {

            routes?.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }

    // ------------------------------

    @SerializedName("Cidades")
    private var cities : MutableList<String> = mutableListOf()



    fun getCities(): MutableList<String> {
        return cities
    }

    fun setCities(cities: MutableList<String>) {
        this.cities = cities
    }

    fun addCity(newCity: String): Boolean {
        return try {

            cities.add(newCity)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removeCity(index: Int): Boolean {

        return try {

            cities.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }

    constructor(parcel: Parcel) : this() {

        routes = parcel.createStringArrayList()
        guides = parcel.createStringArrayList()
        cities = parcel.createStringArrayList()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(routes)
        parcel.writeStringList(guides)
        parcel.writeStringList(cities)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavoriteObject> {
        override fun createFromParcel(parcel: Parcel): FavoriteObject {
            return FavoriteObject(parcel)
        }

        override fun newArray(size: Int): Array<FavoriteObject?> {
            return arrayOfNulls(size)
        }
    }

}