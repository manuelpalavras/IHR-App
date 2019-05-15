package com.example.ihr.api.model.routeprogress

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import com.example.ihr.api.model.route.RouteObject
import com.google.gson.JsonArray
import com.mapbox.geojson.Point
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*

class RouteProgressObject() : Parcelable {

    private var routeName: String = ""
    private lateinit var pointCheckerList: Array<PointChecker?>
    private var duration: Double = 0.0
    private var distance: Double = 0.0
    private var rating: Int = 0
    private var startTime: String = ""

    constructor(rota: RouteObject, startingPoint: Point) : this() {
        this.routeName = rota.getName()
        this.pointCheckerList = arrayOfNulls(rota.getPoi().size + 1)
        addPointChecker(0, PointChecker("Ponto Inicial", getCurrentTime(), startingPoint))
        this.startTime = getCurrentTime()
    }

    fun addPointChecker(index: Int, pointChecker: PointChecker?) {
        pointCheckerList[index] = pointChecker
    }

    fun getDuration(): Double {
        return duration
    }

    fun getDistance(): Double {
        return distance
    }

    fun setDuration(duration: Double) {
        this.duration = duration
    }

    fun setDistance(distance: Double) {
        this.distance = distance
    }

    fun setRating(rating: Int) {
        this.rating = rating
    }

    fun getPointCheckerByIndex(i: Int): PointChecker? {
        return pointCheckerList[i]
    }

    fun getPointCheckerList(): Array<PointChecker?> {
        return pointCheckerList
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val cal = Calendar.getInstance()
        return dateFormat.format(cal.time)
    }

    fun getName(): String {
        return routeName
    }


    fun toJSON(): JSONObject {

        val json = JSONObject()
        val jsonArray = mutableListOf<JSONObject>()

        json.put("Rota", routeName)
        json.put("HoraComeco", getCurrentTime())
        json.put("Duracao", duration)
        json.put("Distancia", distance.toInt())
        json.put("Classificacao", rating)

        pointCheckerList.forEach {
            val jsonPoI = JSONObject()
            jsonPoI.put("Nome", it?.getName())
            jsonPoI.put("Comentario", it?.getCommentary())

            val jsonCoord = JSONObject()
            try {
                jsonCoord.put("type", "Point")
                jsonCoord.put("coordinates", mutableListOf(it?.getPoint()?.latitude(), it?.getPoint()?.longitude()))
                jsonPoI.put("coordenadas", jsonCoord)
            } catch (e: Exception) {

            }

            jsonPoI.put("Classificacao", it?.getRating())
            jsonPoI.put("Hora", it?.getTime())


            jsonArray.add(jsonPoI)
        }

        json.put("Pontos de Interesse", jsonArray)


        return json

    }

    fun printToConsole() {
        System.out.printf(" \n\n\n JSON GERADO \n" + this.toJSON().toString() + "\n\n")
    }

    constructor(parcel: Parcel) : this() {
        routeName = parcel.readString()
        pointCheckerList = parcel.createTypedArray(PointChecker)
        duration = parcel.readDouble()
        distance = parcel.readDouble()
        rating = parcel.readInt()
        startTime = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(routeName)
        parcel.writeTypedArray(pointCheckerList, flags)
        parcel.writeDouble(duration)
        parcel.writeDouble(distance)
        parcel.writeInt(rating)
        parcel.writeString(startTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RouteProgressObject> {
        override fun createFromParcel(parcel: Parcel): RouteProgressObject {
            return RouteProgressObject(parcel)
        }

        override fun newArray(size: Int): Array<RouteProgressObject?> {
            return arrayOfNulls(size)
        }
    }

}