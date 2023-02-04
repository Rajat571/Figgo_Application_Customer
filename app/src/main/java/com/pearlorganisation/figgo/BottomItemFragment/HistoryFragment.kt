package com.pearlorganisation.figgo.BottomItemFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.AdvanceCityAdapter
import com.pearlorganisation.figgo.Adapter.HistoryBottomAdapter
import com.pearlorganisation.figgo.Adapter.RideHistoryRowAdapter
import com.pearlorganisation.figgo.Model.HistoryBottomModelList
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.FragmentHistoryBinding
import androidx.databinding.DataBindingUtil.inflate as inflate1


class HistoryFragment : Fragment() {

    lateinit var historyBottomAdapter: HistoryBottomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_history, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var header: RecyclerView = view.findViewById<RecyclerView>(R.id.ridehistoryheader)


        var headerData = listOf<String>("Id","Name","To location","From Location","Status","View")

        var contentdata = ArrayList<List<String>>()
        contentdata.add(listOf("Booking Id","Name","To location","From Location","Status","View"))

        for (i in 0..40)
            contentdata.add(listOf("1","Figgo","Dehradun","Delhi","Uttar pradesh","View"))
        header.adapter= RideHistoryRowAdapter(contentdata,requireContext())
        header.layoutManager=LinearLayoutManager(requireContext())




    }


}