package com.example.ihr.api.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.ihr.R
import com.example.ihr.api.model.route.PoiObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.model.routeprogress.PointChecker
import com.mapbox.geojson.Point
import com.squareup.picasso.Picasso
import android.app.Activity
import android.content.Intent



class PoiArrival : AppCompatActivity() {

    private lateinit var namePoI: TextView
    private lateinit var close: Button
    private lateinit var poiImage: ImageView
    private lateinit var descriptionPoI: TextView
    private lateinit var starOne: ImageButton
    private lateinit var starTwo: ImageButton
    private lateinit var starThree: ImageButton
    private lateinit var starFour: ImageButton
    private lateinit var starFive: ImageButton

    private lateinit var commentary: EditText
    private lateinit var poiArrived: PoiObject

    private var ratingBar: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi_arrival)

        poiArrived = intent.extras.getParcelable("poi")

        close = findViewById(R.id.close)
        namePoI = findViewById(R.id.namePoI)
        poiImage = findViewById(R.id.poiImage)
        descriptionPoI = findViewById(R.id.descriptionPoI)

        starOne = findViewById(R.id.starOne)
        starTwo = findViewById(R.id.starTwo)
        starThree = findViewById(R.id.starThree)
        starFour = findViewById(R.id.starFour)
        starFive = findViewById(R.id.starFive)

        commentary = findViewById(R.id.commentary)

        starOne.setImageResource(R.drawable.heartblack)
        starTwo.setImageResource(R.drawable.heartblack)
        starThree.setImageResource(R.drawable.heartblack)
        starFour.setImageResource(R.drawable.heartblack)
        starFive.setImageResource(R.drawable.heartblack)

        starOne.setOnClickListener { starClicked(starOne) }
        starTwo.setOnClickListener { starClicked(starTwo) }
        starThree.setOnClickListener { starClicked(starThree) }
        starFour.setOnClickListener { starClicked(starFour) }
        starFive.setOnClickListener { starClicked(starFive) }
        close.setOnClickListener {
            val intent = Intent()
            intent.putExtra("result", PointChecker(poiArrived.getName(),commentary.text.toString(),ratingBar))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        namePoI.text = poiArrived.getName()
        Picasso.get().load(ServerConnector.address + "/image/Imagens/" + poiArrived.getImage()).into(poiImage)
        descriptionPoI.text = poiArrived.getDescription()

    }

    private fun starClicked(button: ImageButton) {

        when (button) {

            starOne -> {
                starOne.setImageResource(R.drawable.heartgreen)
                starTwo.setImageResource(R.drawable.heartblack)
                starThree.setImageResource(R.drawable.heartblack)
                starFour.setImageResource(R.drawable.heartblack)
                starFive.setImageResource(R.drawable.heartblack)
                ratingBar = 1
            }

            starTwo -> {
                starOne.setImageResource(R.drawable.heartgreen)
                starTwo.setImageResource(R.drawable.heartgreen)
                starThree.setImageResource(R.drawable.heartblack)
                starFour.setImageResource(R.drawable.heartblack)
                starFive.setImageResource(R.drawable.heartblack)
                ratingBar = 2
            }

            starThree -> {
                starOne.setImageResource(R.drawable.heartgreen)
                starTwo.setImageResource(R.drawable.heartgreen)
                starThree.setImageResource(R.drawable.heartgreen)
                starFour.setImageResource(R.drawable.heartblack)
                starFive.setImageResource(R.drawable.heartblack)
                ratingBar = 3
            }

            starFour -> {
                starOne.setImageResource(R.drawable.heartgreen)
                starTwo.setImageResource(R.drawable.heartgreen)
                starThree.setImageResource(R.drawable.heartgreen)
                starFour.setImageResource(R.drawable.heartgreen)
                starFive.setImageResource(R.drawable.heartblack)
                ratingBar = 4
            }

            starFive -> {
                starOne.setImageResource(R.drawable.heartgreen)
                starTwo.setImageResource(R.drawable.heartgreen)
                starThree.setImageResource(R.drawable.heartgreen)
                starFour.setImageResource(R.drawable.heartgreen)
                starFive.setImageResource(R.drawable.heartgreen)
                ratingBar = 5
            }
        }

    }

}
