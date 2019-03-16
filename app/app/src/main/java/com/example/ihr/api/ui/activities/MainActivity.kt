package com.example.ihr.api.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.example.ihr.R
import com.example.ihr.api.model.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.model.User.AppointmentObject
import com.example.ihr.api.model.User.AvailabilityObject
import com.example.ihr.api.model.User.UserObject
import com.example.ihr.api.service.RoutesClient
import com.example.ihr.api.ui.fragments.RouteFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList



class MainActivity : AppCompatActivity() {

    var retrofit = ServerConnector.instance



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listView : ListView = findViewById(R.id.routeList)
        var list : ArrayList<RouteObject> = ArrayList()

        var routeOne = RouteObject("nome")
        var routeTwo = RouteObject("nome")
        var routeThree= RouteObject("nome")

        list.add(routeOne)
        list.add(routeTwo)
        list.add(routeThree)
        list.add(routeOne)
        list.add(routeTwo)
        list.add(routeThree)

        val fragment = RouteFragment(this,R.layout.route_fragment,list)

        listView.adapter = fragment

    }
}

//    fun escrever() {
//
//        val call: Call<UserObject> =
//            retrofit.getUserClient().getUser("tiagoferreira97@gmail.com") // definir o pedido que vai ser feito
//
//        call.enqueue(object : Callback<UserObject> {
//            override fun onResponse(call: Call<UserObject>, response: Response<UserObject>) {
//
//            }
//            override fun onFailure(call: Call<UserObject>, t: Throwable) {
//
//            }
//        })
//    }

    /*fun toStringUserObject(user: UserObject?): String {


        var availabilities = ""

        user?.getAvailability()?.forEach { availability: AvailabilityObject ->
            availabilities = availabilities + availability.getDate() + "\n" +
                    availability.getCity() + "\n" + "\n"
        }

        var appointments = ""

        user?.getAppointment()?.forEach { appointment: AppointmentObject ->
            appointments = appointments + appointment.getDate() + "\n" +
                    appointment.getPlace() + "\n" +
                    appointment.getIdUser() + "\n" +
                    appointment.getState() + "\n" + "\n"
        }

        return "" +
                user?.getName() + "\n" +
                user?.getEmail() + "\n" +
                user?.getNacionality() + "\n" +
                user?.getBirthDate() + "\n" +
                user?.getFavorite()?.getRoute() + "\n" +
                user?.getFavorite()?.getGuides() + "\n" +
                user?.getFavorite()?.getCities() + "\n" +
                user?.getGuide() + "\n" +
                "AVAILABILITY" + "\n" + availabilities +
                "APPOINTMENTS" + "\n" + appointments


    }*/

