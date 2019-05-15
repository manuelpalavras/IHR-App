package com.example.ihr.api.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.model.route.RouteObject
import com.example.ihr.api.model.routeprogress.PointChecker
import com.example.ihr.api.model.routeprogress.RouteProgressObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_route_rating.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser




class RouteRating : AppCompatActivity() {

    private var userClient = ServerConnector.userClient
    private var routesClient = ServerConnector.routesClient

    private lateinit var nameRoute: TextView
    private lateinit var close: Button
    private lateinit var routeImage: ImageView
    private lateinit var statsRoute: TextView
    private lateinit var starOne: ImageButton
    private lateinit var starTwo: ImageButton
    private lateinit var starThree: ImageButton
    private lateinit var starFour: ImageButton
    private lateinit var starFive: ImageButton

    private var ratingBar: Int = 0
    private lateinit var routeDone: RouteProgressObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_rating)

        routeDone = intent.extras.getParcelable("rota")
        val image = intent.extras.getString("imagem")

        close = findViewById(R.id.close)
        nameRoute = findViewById(R.id.nameRoute)
        routeImage = findViewById(R.id.routeImage)
        statsRoute = findViewById(R.id.statsRoute)

        starOne = findViewById(R.id.starOne)
        starTwo = findViewById(R.id.starTwo)
        starThree = findViewById(R.id.starThree)
        starFour = findViewById(R.id.starFour)
        starFive = findViewById(R.id.starFive)

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
            routeDone.setRating(ratingBar)

            val map = JsonObject()
            val parser = JsonParser().parse(routeDone.toJSON().toString()).asJsonObject
            map.addProperty("json",parser.toString())
            map.addProperty("email","manuelpalavras19@gmail.com")


            System.out.println(Gson().toJson(map) + "\n\n\n\n")
            System.out.println(map.toString() + "\n\n\n\n")

            val postHistory: Call<Boolean> = userClient.postHistory(map)

            postHistory.enqueue(object : Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    System.out.printf(t.message)
                }

                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    System.out.printf(response.message())
                }
            })
            finish()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Removes other Activities from stack
            startActivity(intent)
        }

        nameRoute.text = routeDone.getName()
        Picasso.get().load(ServerConnector.address + "/image/Imagens/" + image).into(routeImage)
        statsRoute.text = "Estatisticas: \n\n Distância:" + routeDone.getDistance().toInt()


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
