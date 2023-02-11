package com.figgo.customer.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.figgo.customer.pearlLib.PrefManager
import com.figgo.customer.R
import com.figgo.customer.UI.DashBoard
import com.figgo.customer.databinding.FragmentMPinGenerateBinding

class MPinGenerate : Fragment() {
    lateinit var binding: FragmentMPinGenerateBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_m_pin_generate, container, false)
        var view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mPin = view.findViewById<EditText>(R.id.mPin)
        var confirm_mpin = view.findViewById<EditText>(R.id.confirm_mpin)
        mPin.requestFocus()
        mPin.setInputType(InputType.TYPE_CLASS_NUMBER)
        var pref = PrefManager(requireContext())
        var mpin = view.findViewById<EditText>(R.id.mPin)
        //var confirm = view.findViewById<EditText>(R.id.confirm_mpin)
        if (mpin.text.toString().equals(confirm_mpin.text.toString())) {
            pref.setMpin(mpin.text.toString())
        } else {

            Toast.makeText(requireContext(), "PIN not match", Toast.LENGTH_SHORT).show()
        }
        binding.continuetv.setOnClickListener {

            startActivity(Intent(context, DashBoard::class.java))

        }
    }


}