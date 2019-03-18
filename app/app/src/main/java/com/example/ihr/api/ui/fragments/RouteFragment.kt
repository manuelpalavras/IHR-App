package com.example.ihr.api.ui.fragments

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

class RouteFragment(context: Context, resource: Int, objects: List<RouteObject>?) :
    ArrayAdapter<RouteObject>(context, resource, objects) , Serializable {

    val ctx: Context = context
    val list: List<RouteObject>? = objects

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(ctx)

        val view: View = inflater.inflate(R.layout.route_fragment, null)

        val routeImage: ImageView = view.findViewById(R.id.poiImage)
        val nomeRoute: TextView = view.findViewById(R.id.nomePoi)
        val starRoute: TextView = view.findViewById(R.id.descriptionPoi)
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

