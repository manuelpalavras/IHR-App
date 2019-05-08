package com.example.ihr.api.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.ihr.R
import com.example.ihr.api.model.route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.ui.components.PoiTextComponent
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
        val listPoI : ListView = findViewById(R.id.listPoI)
        val numPoi : TextView = findViewById(R.id.numPoI)
        val listOfPoI : TextView = findViewById(R.id.listOfPoi)

        var string = ""

        routeSelected?.getType()?.forEach {
            string += it + " "
        }

        numPoi.text = "Número de PoI : " + routeSelected.getPoi().size.toString()
        typeRoute.text = "Tipo: "+ string
        descriptionRoute.text = "Descrição :\n" + routeSelected?.getDescription()
        nameRoute.text = routeSelected?.getName()
        classificationRoute.text = "Classificação : " + routeSelected?.getClassification().toString()
        durationRoute.text = "Duração : " + routeSelected?.getDuration()
        ratingNumberRoute.text = "Número de Avaliações : " + routeSelected?.getEvaluation().toString()
        difficultyRoute.text = "Dificuldade : " + routeSelected?.getDifficulty()
        cityRoute.text = "Cidade : " + routeSelected?.getCity()
        Picasso.get().load(ServerConnector.address + "/image/Imagens/" + routeSelected?.getImage().toString()).resize(150, 150).into(imageRoute)
        listOfPoI.text = "Pontos de Interesse desta Rota : "

        val component = PoiTextComponent(this@RouteActivity,R.layout.poi_textview,routeSelected.getPoi())

        listPoI.adapter = component



        buttonToMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("rota", this.intent.extras.getParcelable<RouteObject>("rota"))
            intent.putExtras(bundle)
            this.startActivity(intent)
        }




    }
}
