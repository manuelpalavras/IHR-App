package com.example.ihr.api.ui.fragments

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ihr.R
import com.example.ihr.api.model.RouteObject
import com.example.ihr.api.ui.activities.MainActivity


class RouteFragment(context: Context, resource: Int, objects: MutableList<RouteObject>) :
    ArrayAdapter<RouteObject>(context, resource, objects) {


    val ctx: Context = context
    val resr: Int = resource
    val list: List<RouteObject> = objects


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(ctx)

        val view: View = inflater.inflate(R.layout.route_fragment, null)


        val nomeRoute: TextView = view.findViewById(R.id.nomeRoute)
        val starRoute: TextView = view.findViewById(R.id.starRoute)
        val categoryRoute: TextView = view.findViewById(R.id.categoryRoute)

        nomeRoute.text = list[position].getName()
        starRoute.text = list[position].getName() + "dois"
        categoryRoute.text = list[position].getName() + "três"

        nomeRoute.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            this.context.startActivity(intent)
        }

        return view
    }

//    private lateinit var imageView: ImageView
//    private lateinit var nameRoute: TextView
//    private lateinit var starRoute: TextView
//    private lateinit var categoryRoute: TextView
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.route_fragment, container, false)
//        nameRoute = view.findViewById(R.id.nomeRoute)
//        imageView = view.findViewById(R.id.routeImage)
//        starRoute = view.findViewById(R.id.starRoute)
//        categoryRoute = view.findViewById(R.id.categoryRoute)
//
//
//        return view
//    }
//
//    fun changeInfo(name: String, star: Int, categories: ArrayList<String>) {
//
//        var string = ""
//        nameRoute.text = name;
//
//        for (i in 1..star)
//            string += "☆";
//
//        starRoute.text = string
//
//        string = ""
//
//        for (category in categories)
//            string = "$string$category,"
//    }

}