package com.pearlorganisation.figgo.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.CabCategoryAdapter
import com.pearlorganisation.figgo.Adapter.FiggoAddAdapter
import com.pearlorganisation.figgo.Model.CabCategory
import com.pearlorganisation.figgo.Model.FiggoAdd
import com.pearlorganisation.figgo.R


class HomeDashboard : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // val recyclerView: RecyclerView = view.findViewById(R.id.figgo_add_list)
        var cabCategoryAdapter: CabCategoryAdapter
        var cab_category_list=ArrayList<CabCategory>()
        val recycler: RecyclerView = view.findViewById(R.id.cab_category_list)
        val figgoAddList = view.findViewById<RecyclerView>(R.id.figgo_add_list)
        var cabCategoryList = view.findViewById<RecyclerView>(R.id.cab_category_list)
        lateinit var figgoAddAdapter: FiggoAddAdapter
        var figgoAddList1=ArrayList<FiggoAdd>()
        figgoAddList1.add(FiggoAdd(R.drawable.figgoadd))
        figgoAddList1.add(FiggoAdd(R.drawable.figgoadd))
        figgoAddList1.add(FiggoAdd(R.drawable.figgoadd))


        figgoAddList.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        figgoAddAdapter= FiggoAddAdapter(figgoAddList1)

       // recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        //recycler.adapter=figgoAddAdapter
        figgoAddList.adapter=figgoAddAdapter
        figgoAddAdapter.notifyDataSetChanged()



        cabCategoryList.layoutManager= GridLayoutManager(requireContext(),4)
        cab_category_list.add(CabCategory(R.drawable.citycab,"City-Cab"))
        cab_category_list.add(CabCategory(R.drawable.outstationcab,"Outstation"))
        cab_category_list.add(CabCategory(R.drawable.fadesharecab,"Share-Cab"))
        cab_category_list.add(CabCategory(R.drawable.airportcab,"Airpot-Cab"))
        cab_category_list.add(CabCategory(R.drawable.royalcab,"Royal-cab"))
        cab_category_list.add(CabCategory(R.drawable.fadetour,"Tour-plan"))
        cab_category_list.add(CabCategory(R.drawable.goodsparcel,"Goods vechile"))
        cab_category_list.add(CabCategory(R.drawable.fadehote,"Hotel"))
        cab_category_list.add(CabCategory(R.drawable.airportcab12,"Flight"))
        cab_category_list.add(CabCategory(R.drawable.fadetrain,"Train"))
        cab_category_list.add(CabCategory(R.drawable.fadebus,"Micro Bus"))
        cab_category_list.add(CabCategory(R.drawable.fademore,"More"))
        cabCategoryAdapter= CabCategoryAdapter(requireContext(),cab_category_list)
      //  recyclerView.layoutManager= GridLayoutManager(requireContext(),4)
     //   recyclerView.adapter=figgoAddAdapter
        cabCategoryList.adapter=cabCategoryAdapter


    }

}