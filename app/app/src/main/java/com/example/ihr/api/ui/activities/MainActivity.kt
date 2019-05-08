package com.example.ihr.api.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.ihr.R
import com.example.ihr.R.drawable.*
import com.example.ihr.api.model.route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.model.user.UserObject
import com.example.ihr.api.ui.components.RouteComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var routesClient = ServerConnector.routesClient
    private var userClient = ServerConnector.userClient

    private lateinit var routeList: List<RouteObject>
    private lateinit var user: UserObject

    private lateinit var exploreButton : ImageButton
    private lateinit var favoriteButton : ImageButton
    private lateinit var scheduleButton : ImageButton
    private lateinit var userInfoButton : ImageButton

    // exploreButton view
    private lateinit var listView : ListView

    //calendarView view
    private lateinit var calendarView : CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        exploreButton = findViewById(R.id.exploreButton)
        favoriteButton = findViewById(R.id.favoriteButton)
        scheduleButton = findViewById(R.id.scheduleButton)
        userInfoButton = findViewById(R.id.userButton)
        listView = findViewById(R.id.listView)

        calendarView = findViewById(R.id.calendarView)


        exploreButton.setOnClickListener { showExplore() }
            favoriteButton.setOnClickListener { showFavorites() }
            scheduleButton.setOnClickListener { showScheduler() }
            userInfoButton.setOnClickListener { showUser() }

            val callRoutes: Call<List<RouteObject>> = routesClient.getAllRoutes()
            val callUser: Call<UserObject> = userClient.getUser("manuelpalavras19@gmail.com")

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
        setButtonColor(favoriteButton)
        listView.visibility = View.INVISIBLE
        calendarView.visibility = View.INVISIBLE

    }

    private fun showExplore() {
        setButtonColor(exploreButton)
        calendarView.visibility = View.INVISIBLE
        try {
            listView.visibility = View.VISIBLE
            listView.adapter = RouteComponent(this@MainActivity, R.layout.route_component, routeList)
        } catch (e : Exception) {

        }
    }

    private fun showUser () {
        setButtonColor(userInfoButton)
        listView.visibility = View.INVISIBLE
        calendarView.visibility = View.INVISIBLE

    }

    private fun showScheduler () {
        setButtonColor(scheduleButton)
        listView.visibility = View.INVISIBLE
        calendarView.visibility = View.VISIBLE
    }

    private fun setButtonColor (button : ImageButton) {

        when(button) {

            exploreButton -> {
                exploreButton.setImageResource(exploregreen)
                favoriteButton.setImageResource(heartblack)
                scheduleButton.setImageResource(schedulerblack)
                userInfoButton.setImageResource(userblack)
            }

            favoriteButton -> {
                exploreButton.setImageResource(exploreblack)
                favoriteButton.setImageResource(heartgreen)
                scheduleButton.setImageResource(schedulerblack)
                userInfoButton.setImageResource(userblack)
            }

            scheduleButton -> {
                exploreButton.setImageResource(exploreblack)
                favoriteButton.setImageResource(heartblack)
                scheduleButton.setImageResource(schedulergreen)
                userInfoButton.setImageResource(userblack)
            }

            userInfoButton -> {
                exploreButton.setImageResource(exploreblack)
                favoriteButton.setImageResource(heartblack)
                scheduleButton.setImageResource(schedulerblack)
                userInfoButton.setImageResource(usergreen)
            }

        }

    }
}



