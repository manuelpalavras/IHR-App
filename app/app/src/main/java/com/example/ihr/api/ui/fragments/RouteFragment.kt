package com.example.ihr.api.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.Route.RouteObject
import com.example.ihr.api.ui.activities.RoteActivity
import java.io.Serializable
import android.graphics.drawable.Drawable
import com.example.ihr.api.model.ServerConnector
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream


class RouteFragment(context: Context, resource: Int, objects: List<RouteObject>?) :
    ArrayAdapter<RouteObject>(context, resource, objects), Serializable {

    val ctx: Context = context
    val list: List<RouteObject>? = objects


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(ctx)

        val view: View = inflater.inflate(R.layout.route_fragment, null)

        val routeImage: ImageView = view.findViewById(R.id.poiImage)
        val nomeRoute: TextView = view.findViewById(R.id.nomePoi)
        val starRoute: TextView = view.findViewById(R.id.descriptionPoi)
        val categoryRoute: TextView = view.findViewById(R.id.categoryRoute)

        Picasso.get().load(ServerConnector.address + "/image/Imagens/" + list?.get(position)?.getImage().toString()).resize(150, 150).into(routeImage)
        nomeRoute.text = list?.get(position)?.getName()
        starRoute.text = list?.get(position)?.getClassification().toString()
        categoryRoute.text = list?.get(position)?.getType().toString()
//
        view.setOnClickListener {
            val intent = Intent(this.context, RoteActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("rota", list?.get(position))
            intent.putExtras(bundle)
            this.context.startActivity(intent)
        }

        return view
    }

}

