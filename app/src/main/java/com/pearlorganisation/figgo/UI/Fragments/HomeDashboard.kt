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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeDashboard.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeDashboard : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.figgo_add_list)
        var cabCategoryAdapter: CabCategoryAdapter
        var cab_category_list=ArrayList<CabCategory>()
        val recycler: RecyclerView = view.findViewById(R.id.cab_category_list)
        val figgoAddList = view.findViewById<RecyclerView>(R.id.figgo_add_list)
        var cabCategoryList = view.findViewById<RecyclerView>(R.id.cab_category_list)
        lateinit var figgoAddAdapter: FiggoAddAdapter
        var figgoAddList1=ArrayList<FiggoAdd>()

        figgoAddList.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        figgoAddList1.add(FiggoAdd(R.drawable.figgoadd))
        figgoAddList1.add(FiggoAdd(R.drawable.figgoadd))
        figgoAddList1.add(FiggoAdd(R.drawable.figgoadd))
        figgoAddAdapter= FiggoAddAdapter(figgoAddList1)

        recycler.layoutManager = GridLayoutManager(requireContext(),3)
        recycler.adapter=figgoAddAdapter
        figgoAddList.adapter=figgoAddAdapter



        cabCategoryList.layoutManager= GridLayoutManager(requireContext(),4)
        cab_category_list.add(CabCategory(R.drawable.citycab,"City-Cab"))
        cab_category_list.add(CabCategory(R.drawable.outstationcab,"Outstation"))
        cab_category_list.add(CabCategory(R.drawable.fadesharecab,"Share-Cab"))
        cab_category_list.add(CabCategory(R.drawable.airportcab,"Airpot-Cab"))
        cab_category_list.add(CabCategory(R.drawable.royalcab,"Royal-cab"))
        cab_category_list.add(CabCategory(R.drawable.fadetour,"Tour-plan"))
        cab_category_list.add(CabCategory(R.drawable.goodsparcel,"Goods vechile"))
        cab_category_list.add(CabCategory(R.drawable.fadehote,"Hotel"))
        cab_category_list.add(CabCategory(R.drawable.fadeflight,"Flight"))
        cab_category_list.add(CabCategory(R.drawable.fadetrain,"Train"))
        cab_category_list.add(CabCategory(R.drawable.fadebus,"Micro Bus"))
        cab_category_list.add(CabCategory(R.drawable.fademore,"More"))
        cabCategoryAdapter= CabCategoryAdapter(requireContext(),cab_category_list)
        recyclerView.layoutManager= GridLayoutManager(requireContext(),4)
        recyclerView.adapter=figgoAddAdapter
        cabCategoryList.adapter=cabCategoryAdapter


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeDashboard.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeDashboard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}