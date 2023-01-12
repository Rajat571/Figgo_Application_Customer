package com.pearlorganisation.figgo.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.pearlorganisation.figgo.Adapter.AdvanceCityAdapter
import com.pearlorganisation.figgo.Model.AdvanceCityCab
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.FragmentPackageCityCabBinding

class Package_cityCab : Fragment() {
    /*lateinit var binding:FragmentPackageCityCabBinding
    lateinit var advanceCityAdapter: AdvanceCityAdapter
    var package_city_list=ArrayList<AdvanceCityCab>()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.package_work_progress, container, false)

      /*binding= DataBindingUtil.inflate(inflater, R.layout.fragment_package_city_cab, container, false)
        return binding.root*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* binding.recyclerPackageList.layoutManager=GridLayoutManager(context,3)
       *//* package_city_list.add(AdvanceCityCab(R.drawable.figgo_auto,"Rs. 2500/-"))
        package_city_list.add(AdvanceCityCab(R.drawable.figgo_bike,"Rs. 1500/-"))
        package_city_list.add(AdvanceCityCab(R.drawable.figgo_lux,"Rs. 4500/-"))
        package_city_list.add(AdvanceCityCab(R.drawable.figgo_e_rick,"Rs. 2500/-"))
        package_city_list.add(AdvanceCityCab(R.drawable.figgo_auto,"Rs. 1500/-"))
        package_city_list.add(AdvanceCityCab(R.drawable.figgo_lux,"Rs. 2500/-"))*//*
        advanceCityAdapter=AdvanceCityAdapter(requireActivity(),package_city_list)
        binding.recyclerPackageList.adapter=advanceCityAdapter*/
    }
}