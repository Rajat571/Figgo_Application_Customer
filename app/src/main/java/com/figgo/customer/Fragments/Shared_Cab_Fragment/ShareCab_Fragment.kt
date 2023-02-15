package com.figgo.customer.Fragments.Shared_Cab_Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.figgo.customer.Adapter.AdvanceCityAdapter
import com.figgo.customer.Model.AdvanceCityCab
import com.figgo.customer.R

class ShareCab_Fragment : Fragment() {
    lateinit var advanceCityAdapter: AdvanceCityAdapter
    var cablist=ArrayList<AdvanceCityCab>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_cab_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var share_cab_list=view.findViewById<RecyclerView>(R.id.share_cab_list)
        share_cab_list.layoutManager= GridLayoutManager(context,4)
       /* cablist.add(AdvanceCityCab(R.drawable.figgo_auto,"75-100"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_bike,"45-65"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_e_rick,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_lux,"125-400"))*/
        advanceCityAdapter=AdvanceCityAdapter(requireActivity() ,cablist)
        share_cab_list.adapter=advanceCityAdapter

        var live=view.findViewById<TextView>(R.id.live)
        var advance=view.findViewById<TextView>(R.id.advance)
        live.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_shareCab_Fragment_to_liveSharecab_Fragment)
        }
        advance.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_shareCab_Fragment_to_advanceSharecab_Fragment)
        }
    }

}