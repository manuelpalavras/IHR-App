package com.example.ihr.api.model.routeprogress

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.mapbox.geojson.Point
import java.text.SimpleDateFormat
import java.util.*

class PointChecker() : Parcelable {


    private var poiName: String = ""
    private var commentary: String = ""
    private var rating: Int = 0
    private var duration: Double = 0.0
    private var time: String = ""
    private lateinit var point: Point

    fun addDuration(duration: Double) {
        this.duration = duration
    }

    constructor(poiName: String, commentary: String, rating: Int) : this() {
        this.poiName = poiName
        this.commentary = commentary
        this.rating = rating
        this.time = getCurrentTime()
    }

    constructor(poiName: String, commentary: String, rating: Int, point: Point) : this() {
        this.poiName = poiName
        this.commentary = commentary
        this.rating = rating
        this.point = point
        this.time = getCurrentTime()
    }

    constructor(poiName: String, time: String, point: Point) : this() {
        this.poiName = poiName
        this.point = point
        this.time = time
    }


    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val cal = Calendar.getInstance()
        return dateFormat.format(cal.time)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(poiName)
        parcel.writeString(commentary)
        parcel.writeInt(rating)
        parcel.writeDouble(duration)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PointChecker> {
        override fun createFromParcel(parcel: Parcel): PointChecker {
            return PointChecker(parcel)
        }

        override fun newArray(size: Int): Array<PointChecker?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this() {
        poiName = parcel.readString()
        commentary = parcel.readString()
        rating = parcel.readInt()
        duration = parcel.readDouble()
        time = parcel.readString()
    }

    fun getName(): String {
        return poiName
    }

    fun getCommentary() :String {
        return commentary
    }

    fun getRating() : Int {
        return rating
    }

    fun getTime() : String {
        return time
    }

    fun getPoint(): Point {
        return point
    }


}