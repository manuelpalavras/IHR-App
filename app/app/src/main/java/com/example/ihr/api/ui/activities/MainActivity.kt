package com.example.ihr.api.ui.activities

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.ihr.R
import com.example.ihr.R.drawable.*


import com.example.ihr.api.model.Route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.model.User.UserObject
import com.example.ihr.api.ui.components.RouteComponent
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var routesClient = ServerConnector.routesClient
    private var userClient = ServerConnector.userClient

    private lateinit var routeList: List<RouteObject>
    private lateinit var user: UserObject


    private lateinit var content : ConstraintLayout
    private lateinit var explore : ImageButton
    private lateinit var favorite : ImageButton
    private lateinit var schedule : ImageButton
    private lateinit var userInfo : ImageButton

    // explore view
    private lateinit var listView : ListView

    //scheduler view
    private lateinit var scheduler : CalendarView
    private lateinit var routeName : TextView
    private lateinit var descriptionRoute : TextView
    private lateinit var routeImage : ImageView

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        content = findViewById(R.id.content)
        explore = findViewById(R.id.explore)
        favorite = findViewById(R.id.favorite)
        schedule = findViewById(R.id.schedule)
        userInfo = findViewById(R.id.user)
        listView = findViewById(R.id.listView)
        routeName = findViewById(R.id.routeName)
        descriptionRoute = findViewById(R.id.descriptionRoute)
        routeImage = findViewById(R.id.routeImage)
        scheduler = findViewById(R.id.scheduler)


        explore.setOnClickListener { showExplore() }
        favorite.setOnClickListener { showFavorites() }
        schedule.setOnClickListener { showScheduler() }
        userInfo.setOnClickListener { showUser() }

        val callRoutes: Call<List<RouteObject>> = routesClient.getAllRoutes()
        val callUser : Call<UserObject> = userClient.getUser("manuelpalavras19@gmail.com")

        callRoutes.enqueue(object : Callback<List<RouteObject>> {
            override fun onFailure(call: Call<List<RouteObject>>, t: Throwable) {

                }

            override fun onResponse(call: Call<List<RouteObject>>, response: Response<List<RouteObject>>) {
                routeList = response.body()!!
                showExplore()
            }


        })

        callUser.enqueue(object : Callback<UserObject> {
            override fun onFailure(call: Call<UserObject>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserObject>, response: Response<UserObject>) {
                user = response.body()!!
            }

        })




    }

    private fun showFavorites() {
        setButtonColor(favorite)
        listView.visibility = View.INVISIBLE
        content.visibility = View.INVISIBLE

    }

    private fun showExplore() {
        setButtonColor(explore)
        content.visibility = View.INVISIBLE
        try {
            listView.visibility = View.VISIBLE
            val component = RouteComponent(context, R.layout.route_component, routeList)
            listView.adapter = component
        } catch (e : Exception) {

        }


    }

    private fun showUser () {
        setButtonColor(userInfo)
        listView.visibility = View.INVISIBLE
        content.visibility = View.INVISIBLE

    }

    private fun showScheduler () {
        setButtonColor(schedule)
        listView.visibility = View.INVISIBLE
        content.visibility = View.INVISIBLE

        View.inflate(context,R.layout.calender_component,content)
        content.visibility = View.VISIBLE

        try {
            Picasso.get().load(ServerConnector.address + "/image/Imagens/" + routeList[0].getImage())
                .resize(150, 150).into(routeImage)
            descriptionRoute.text = routeList[0].getDescription()
            routeName.text = routeList[0].getName()
        } catch (e : Exception) {

        }



    }

    private fun setButtonColor (button : ImageButton) {

        when(button) {

            explore -> {
                explore.setImageResource(exploregreen)
                favorite.setImageResource(heartblack)
                schedule.setImageResource(schedulerblack)
                userInfo.setImageResource(userblack)
            }

            favorite -> {
                explore.setImageResource(exploreblack)
                favorite.setImageResource(heartgreen)
                schedule.setImageResource(schedulerblack)
                userInfo.setImageResource(userblack)
            }

            schedule -> {
                explore.setImageResource(exploreblack)
                favorite.setImageResource(heartblack)
                schedule.setImageResource(schedulergreen)
                userInfo.setImageResource(userblack)
            }

            userInfo -> {
                explore.setImageResource(exploreblack)
                favorite.setImageResource(heartblack)
                schedule.setImageResource(schedulerblack)
                userInfo.setImageResource(usergreen)
            }

        }

    }
}



