package com.example.ihr.api.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.example.ihr.R
import com.example.ihr.api.model.Route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.ui.fragments.RouteFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    var routes = ServerConnector.getRoutesClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.routeList)

        val context = this

        val call: Call<List<RouteObject>> = routes.getAllRoutes()
        var list: List<RouteObject>?

        call.enqueue(object : Callback<List<RouteObject>> {
            override fun onFailure(call: Call<List<RouteObject>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<RouteObject>>, response: Response<List<RouteObject>>) {
                list = response.body()

                val fragment = RouteFragment(context, R.layout.route_fragment, list)

                listView.adapter = fragment

            }



        })



    }


}



