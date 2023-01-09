package com.pearlorganisation.figgo.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.RidesAdapter
import com.pearlorganisation.figgo.Model.RidesModel
import com.pearlorganisation.figgo.R


class RidesBottom : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_rides_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data = ArrayList<RidesModel>();
        data.add(RidesModel("26-12-2022","",""))
        data.add(RidesModel("26-12-2022","",""))
        data.add(RidesModel("26-12-2022","",""))
        data.add(RidesModel("26-12-2022","",""))
        var ridesrecycler = view.findViewById<RecyclerView>(R.id.rides_recycler)
        ridesrecycler.adapter =RidesAdapter(data)
        ridesrecycler.layoutManager = LinearLayoutManager(context)

    }

}