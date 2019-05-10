package com.example.ihr.api.model.routeprogress

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.ihr.api.model.route.RouteObject
import com.google.gson.JsonArray
import com.mapbox.geojson.Point
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class RouteProgressObject {

    private var routeName: String
    private var pointCheckerList: MutableList<PointChecker> = mutableListOf()
    private var duration: Double = 0.0
    private var distance: Double = 0.0
    private var rating: Int = 0
    private var startTime: String = ""

    constructor(rota: RouteObject, startingPoint: Point) {
        this.routeName = rota.getName()
        pointCheckerList.add(PointChecker("Ponto Inicial", getCurrentTime(), startingPoint))
        this.startTime = getCurrentTime()
    }

    fun addPointChecker(pointChecker: PointChecker) {
        pointCheckerList.add(pointChecker)
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

    fun getPointCheckerByIndex(i: Int): PointChecker {
        return pointCheckerList[i]
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
        json.put("Distancia", distance)
        json.put("Classificacao", rating)

        pointCheckerList.forEach {
            val jsonPoI = JSONObject()
            jsonPoI.put("Nome", it.getName())
            jsonPoI.put("Comentario", it.getCommentary())

            val jsonCoord = JSONObject()
            try {
                jsonCoord.put("type", "Point")
                jsonCoord.put("coordinates", "[" + it.getPoint().latitude() + "," + it.getPoint().longitude() + "]")
                jsonPoI.put("coordenadas",jsonCoord)
            } catch (e : Exception) {

            }

            jsonPoI.put("Classificacao", it.getRating())
            jsonPoI.put("Hora", it.getTime())


            jsonArray.add(jsonPoI)
        }

        json.put("Pontos de Interesse",jsonArray)


        return json

    }

}