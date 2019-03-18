package com.example.ihr.api.model


import com.example.ihr.api.service.RoutesClient
import com.example.ihr.api.service.UserClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerConnector {

    companion object {

        private val retrofit: Retrofit
            get() {

                val addressHome = "http://192.168.1.4:8080" // default ip address casa
                val addressUni = "http://10.72.10.196:8080" // default ip address universidade, muda todos os dias

                val builder: Retrofit.Builder = Retrofit.Builder()
                    .baseUrl(addressUni)
                    .addConverterFactory(GsonConverterFactory.create()) // definir caracteristas de Retrofit Object

                return builder.build() // criação de Retrofit Object com a build feita
            }

        fun getRoutesClient() : RoutesClient {
                return retrofit.create(RoutesClient::class.java)
            }

        val userClient: UserClient
            get() {
                return retrofit.create(UserClient::class.java)
            }


    }

}

