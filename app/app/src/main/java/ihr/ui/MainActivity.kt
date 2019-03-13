package com.example.exampleapi.ui.adapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.exampleapi.R
import com.example.exampleapi.api.model.User.AppointmentObject
import com.example.exampleapi.api.model.User.AvailabilityObject
import com.example.exampleapi.api.model.User.UserObject
import com.example.exampleapi.api.service.UserClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botao: Button = findViewById<Button>(R.id.botao)
        val addressHome = "http://192.168.1.4:8080" // default ip address casa
        val addressUni = "http://10.72.111.104:8080" // default ip address universidade, muda todos os dias

        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(addressUni)
            .addConverterFactory(GsonConverterFactory.create()) // definir caracteristas de Retrofit Object

        val retrofit: Retrofit = builder.build() // criação de Retrofit Object com a build feita

        val client: UserClient = retrofit.create(UserClient::class.java) // definir o serviço que vai ser utilizado

        botao.setOnClickListener {

            val call: Call<UserObject> =
                client.getUser("tiagoferreira97@gmail.com") // definir o pedido que vai ser feito

            call.enqueue(object : Callback<UserObject> {
                override fun onResponse(call: Call<UserObject>, response: Response<UserObject>) {

                    Toast.makeText(this@MainActivity, toStringUserObject(response.body()), Toast.LENGTH_SHORT + 50)
                        .show()
                }

                override fun onFailure(call: Call<UserObject>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
                    System.out.println(t)
                }
            })
        }
        //val httpclient : OkHttpClient.Builder = OkHttpClient.Builder() // logo se vê

        /*// enqueue = async request
//            override fun onResponse(call: Call<List<RouteObject>>, response: Response<List<RouteObject>>) { // status 200
//                response.body()?.forEach { routeObject: RouteObject ->
//                    Toast.makeText(this@MainActivity, routeObject.getName(), Toast.LENGTH_SHORT).show()
//                }
//            }
//            override fun onFailure(call: Call<List<RouteObject>>, t: Throwable) { // status 300
//                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
//            }*/


    }

    fun toStringUserObject(user: UserObject?): String {


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


    }

}

