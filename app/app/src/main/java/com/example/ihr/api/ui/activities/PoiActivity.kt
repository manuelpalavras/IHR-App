package com.example.ihr.api.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.route.PoiObject
import com.example.ihr.api.model.route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.ui.components.RouteComponent
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoiActivity : AppCompatActivity() {


    private var routesClient = ServerConnector.routesClient
    private lateinit var poiImage: ImageView
    private lateinit var poiName: TextView
    private lateinit var descriptionPoi: TextView
    private lateinit var routesFromPoi : TextView
    private lateinit var listView: ListView

    private lateinit var poiSelected: PoiObject

    private lateinit var routeList: List<RouteObject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi)

        poiSelected = intent.extras.getParcelable("poi")

        poiImage = findViewById(R.id.routeImage)
        poiName = findViewById(R.id.poiName)
        descriptionPoi = findViewById(R.id.poiDescription)
        routesFromPoi = findViewById(R.id.routesFromPoI)
        listView = findViewById(R.id.listView)

        Picasso.get().load(ServerConnector.address + "/image/Imagens/" + poiSelected.getImage()).into(poiImage)
        poiName.text = poiSelected.getName()
        descriptionPoi.text = "Descrição :\n" + poiSelected.getDescription()
        routesFromPoi.text = "Rotas por onde pode passar por " + poiSelected.getName()


        val callRoutes: Call<List<RouteObject>> = routesClient.getRoutesFromPoI(poiSelected.getName())


        callRoutes.enqueue(object : Callback<List<RouteObject>> {
            override fun onFailure(call: Call<List<RouteObject>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<RouteObject>>, response: Response<List<RouteObject>>) {
                routeList = response.body()!!
                listView.adapter = RouteComponent(this@PoiActivity, R.layout.route_component, routeList)


            }


        })

    }
}
