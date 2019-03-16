package com.example.ihr.api.model

import com.example.ihr.api.model.ServerConnector.Companion.instance
import com.example.ihr.api.service.RoutesClient
import com.example.ihr.api.service.UserClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerConnector {

    companion object {
        var instance = ServerConnector
        private set
    }

    private val addressHome = "http://192.168.1.4:8080" // default ip address casa
    private val addressUni = "http://10.72.110.176:8080" // default ip address universidade, muda todos os dias

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(addressUni)
        .addConverterFactory(GsonConverterFactory.create()) // definir caracteristas de Retrofit Object

    private val retrofit: Retrofit = builder.build() // criação de Retrofit Object com a build feita

    fun getRetrofit() : Retrofit {
        return retrofit;
    }

    fun getRoutesClient() : RoutesClient{
        return getRetrofit().create(RoutesClient::class.java)
    }

    fun getUserClient() :UserClient{
        return getRetrofit().create(UserClient::class.java)
    }

    fun getInstance () : Companion {
        return instance
    }


}

