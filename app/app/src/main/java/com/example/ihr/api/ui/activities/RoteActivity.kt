package com.example.ihr.api.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.Route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.ui.fragments.PoiFragment
import com.example.ihr.api.ui.fragments.RouteFragment
import com.squareup.picasso.Picasso

class RoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rote)

        val list = intent.extras.getParcelable<RouteObject>("rota")

        val imageRoute: ImageView = findViewById(R.id.imageRoute)
        val descriptionRoute: TextView = findViewById(R.id.descriptionRoute)
        val nameRoute: TextView = findViewById(R.id.nameRoute)
        val classificationRoute: TextView = findViewById(R.id.classificationRoute)
        val durationRoute: TextView = findViewById(R.id.durationRoute)
        val ratingNumberRoute: TextView = findViewById(R.id.ratingNumberRoute)
        val difficultyRoute: TextView = findViewById(R.id.dificultyRoute)
        val countryRoute: TextView = findViewById(R.id.countryRoute)
        val cityRoute: TextView = findViewById(R.id.cityRoute)
        val listView : ListView = findViewById(R.id.poiList)

        Picasso.get().load(ServerConnector.address + "/image/Imagens/" + list?.getImage().toString()).resize(150, 150).into(imageRoute)
        descriptionRoute.text = list?.getDescription()
        nameRoute.text = list?.getName()
        classificationRoute.text = "CLASSIFICAÇÃO: " + list?.getClassification().toString()
        durationRoute.text = "DURAÇÃO: " + list?.getDuration()
        ratingNumberRoute.text ="AVALIAÇÕES: " + list?.getEvaluation().toString()
        difficultyRoute.text ="DIFICULDADE: " + list?.getDifficulty()
        countryRoute.text = "PAÍS: " + list?.getCountry()
        cityRoute.text ="CIDADE: " + list?.getCity()

        val fragment = PoiFragment(this, R.layout.route_fragment, list?.getPoi())
        listView.adapter = fragment



    }
}
