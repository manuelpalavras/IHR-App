package com.example.ihr.api.ui.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.Route.PoiObject
import com.example.ihr.api.model.Route.RouteObject
import java.io.Serializable

class PoiFragment(context: Context, resource: Int, objects: List<PoiObject>?) :
    ArrayAdapter<PoiObject>(context, resource, objects), Serializable {

    val ctx: Context = context
    val list: List<PoiObject>? = objects

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(ctx)

        val view: View = inflater.inflate(R.layout.poi_fragment, null)

        val poiImage: ImageView = view.findViewById(R.id.poiImage)
        val nomePoi: TextView = view.findViewById(R.id.nomePoi)
        val descriptionPoi: TextView = view.findViewById(R.id.descriptionPoi)

        nomePoi.text = list?.get(position)?.getName()
        descriptionPoi.text = list?.get(position)?.getDescription()

        return view
    }
}