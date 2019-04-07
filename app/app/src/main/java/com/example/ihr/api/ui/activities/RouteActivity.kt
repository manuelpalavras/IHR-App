package com.example.ihr.api.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.Route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.squareup.picasso.Picasso

class RouteActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        val routeSelected = intent.extras.getParcelable<RouteObject>("rota")

        // --------- UI COMPONENTS ------

        val buttonToMap : Button = findViewById(R.id.button)
        val imageRoute: ImageView = findViewById(R.id.imageRoute)
        val descriptionRoute: TextView = findViewById(R.id.descriptionRoute)
        val nameRoute: TextView = findViewById(R.id.nameRoute)
        val classificationRoute: TextView = findViewById(R.id.classificationRoute)
        val durationRoute: TextView = findViewById(R.id.durationRoute)
        val ratingNumberRoute: TextView = findViewById(R.id.ratingNumberRoute)
        val difficultyRoute: TextView = findViewById(R.id.dificultyRoute)
        val cityRoute: TextView = findViewById(R.id.cityRoute)
        val typeRoute: TextView = findViewById(R.id.typeRoute)

        typeRoute.text = routeSelected?.getType().toString()
        descriptionRoute.text = routeSelected?.getDescription()
        nameRoute.text = routeSelected?.getName()
        classificationRoute.text = routeSelected?.getClassification().toString()
        durationRoute.text = routeSelected?.getDuration()
        ratingNumberRoute.text = routeSelected?.getEvaluation().toString()
        difficultyRoute.text = routeSelected?.getDifficulty()
        cityRoute.text = routeSelected?.getCity()
        Picasso.get().load(ServerConnector.address + "/image/Imagens/" + routeSelected?.getImage().toString()).resize(150, 150).into(imageRoute)



        buttonToMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("rota", this.intent.extras.getParcelable<RouteObject>("rota"))
            intent.putExtras(bundle)
            this.startActivity(intent)
        }


    }
}
