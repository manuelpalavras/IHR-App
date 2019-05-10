package com.example.ihr.api.model


import com.example.ihr.api.service.RoutesClient
import com.example.ihr.api.service.UserClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerConnector {

    companion object {

        private val retrofit: Retrofit
            get() {


                val builder: Retrofit.Builder = Retrofit.Builder()
                    .baseUrl(address)
                    .addConverterFactory(GsonConverterFactory.create()) // definir caracteristas de Retrofit Object

                return builder.build() // criação de Retrofit Object com a build feita
            }

        val routesClient: RoutesClient
            get() {

                return retrofit.create(RoutesClient::class.java)
            }

        val userClient: UserClient
            get() {
                return retrofit.create(UserClient::class.java)
            }

       val address: String
            get() {

                val addressHome = "http://192.168.1.2:8080" // default ip address casa
                val addressUni = "http://10.72.122.240:8080" // default ip address universidade, muda todos os dias

                return addressHome
            }


    }

}

