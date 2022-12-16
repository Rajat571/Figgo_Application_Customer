package com.pearlorganisation.figgo.UI.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.DashBoard
import com.pearlorganisation.figgo.databinding.FragmentMPinGenerateBinding

class MPinGenerate : Fragment() {
    lateinit var binding: FragmentMPinGenerateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_m_pin_generate, container, false)
        var view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continuetv.setOnClickListener {
            startActivity(Intent(context,DashBoard::class.java))
        }
    }

}