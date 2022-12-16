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
import com.pearlorganisation.figgo.databinding.FragmentCurrentCityCabBinding

class Current_cityCab : Fragment() {
    lateinit var binding: FragmentCurrentCityCabBinding
    lateinit var advanceCityAdapter: AdvanceCityAdapter
    var cablist=ArrayList<AdvanceCityCab>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_current_city_cab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.currentCabList.layoutManager=GridLayoutManager(context,4)
        cablist.add(AdvanceCityCab(R.drawable.figgo_auto,"75-100"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_bike,"45-65"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_e_rick,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_lux,"125-400"))
        advanceCityAdapter=AdvanceCityAdapter(requireActivity(),cablist)
        binding.currentCabList.adapter=advanceCityAdapter

    }
}