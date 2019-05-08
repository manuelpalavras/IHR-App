package com.example.ihr.api.ui.components;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.route.PoiObject
import com.example.ihr.api.ui.activities.PoiActivity
import java.io.Serializable

class PoiTextComponent(context: Context, resource: Int, objects: List<PoiObject>?) :
    ArrayAdapter<PoiObject>(context, resource, objects), Serializable {

    val ctx: Context = context
    val list: List<PoiObject>? = objects

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = LayoutInflater.from(ctx)
        val view: View = inflater.inflate(R.layout.poi_textview, null)

        val nomePoi: TextView = view.findViewById(R.id.poiName)

        nomePoi.setOnClickListener {
            val intent = Intent(this.context, PoiActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("poi", list?.get(position))
            intent.putExtras(bundle)
            this.context.startActivity(intent)
        }

        nomePoi.text = list?.get(position)?.getName()

        return view
    }

}
