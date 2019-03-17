package com.example.ihr.api.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.media.Image
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.Route.RouteObject
import com.example.ihr.api.model.ServerConnector
import com.example.ihr.api.service.RoutesClient
import com.example.ihr.api.ui.activities.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RouteFragment(context: Context, resource: Int, objects: List<RouteObject>?) :
    ArrayAdapter<RouteObject>(context, resource, objects) {


    val ctx: Context = context
    val resr: Int = resource
    val list: List<RouteObject>? = objects


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(ctx)

        val view: View = inflater.inflate(R.layout.route_fragment, null)

        val routeImage: ImageView = view.findViewById(R.id.routeImage)
        val nomeRoute: TextView = view.findViewById(R.id.nomeRoute)
        val starRoute: TextView = view.findViewById(R.id.starRoute)
        val categoryRoute: TextView = view.findViewById(R.id.categoryRoute)

//        val call : Call<Picture> = ServerConnector.getRoutesClient().getImage(list?.get(position)?.getImage().toString())
//
//        call.enqueue(object : Callback<Picture> {
//            override fun onFailure(call: Call<Picture>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<Picture>, response: Response<Picture>) {
//                val bitmap : Bitmap = Bitmap.createBitmap(response.body())
//                routeImage.setImageBitmap(bitmap)
//            }
//        })

        nomeRoute.text = list?.get(position)?.getName()
        starRoute.text = list?.get(position)?.getClassification().toString()
        categoryRoute.text = list?.get(position)?.getType().toString()
//
//        nomeRoute.setOnClickListener {
//            val intent = Intent(this.context, MainActivity::class.java)
//            this.context.startActivity(intent)
//        }

        return view
    }

}