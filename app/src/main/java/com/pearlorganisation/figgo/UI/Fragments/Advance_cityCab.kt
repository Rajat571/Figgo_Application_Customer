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
import com.pearlorganisation.figgo.databinding.FragmentAdvanceCityCabBinding

class Advance_cityCab : Fragment() {
    lateinit var binding: FragmentAdvanceCityCabBinding
    lateinit var advanceCityAdapter: AdvanceCityAdapter
    var cablist=ArrayList<AdvanceCityCab>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_advance_city_cab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recylerCabList.layoutManager=GridLayoutManager(context,4)
        cablist.add(AdvanceCityCab(R.drawable.figgo_auto,"75-100"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_bike,"45-65"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_e_rick,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.figgo_lux,"125-400"))
        cablist.add(AdvanceCityCab(R.drawable.ola_mini,"256-420"))
        cablist.add(AdvanceCityCab(R.drawable.ola_auto,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.ola_bike,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.ola_e_rick,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.ola_lux,"25-40"))
        cablist.add(AdvanceCityCab(R.drawable.ola_mini,"25-40"))
        advanceCityAdapter=AdvanceCityAdapter(requireActivity(),cablist)
        binding.recylerCabList.adapter=advanceCityAdapter
    }
}