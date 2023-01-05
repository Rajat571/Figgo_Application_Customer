package com.pearlorganisation.figgo.UI.Fragments.Shared_Cab_Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard

class ThankyouScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thankyou_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var next_button = view.findViewById<TextView>(R.id.next_button)
        var book_other = view.findViewById<TextView>(R.id.book_other)
        var close = view.findViewById<TextView>(R.id.close)

        next_button.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_thankyouScreenFragment_to_vehicleNumberFragment)
        }


        var ride_service_rating = view.findViewById<RatingBar>(R.id.ride_service_rating)
//        ride_service_rating.rating
        book_other.setOnClickListener {


            var homeFrag = HomeDashboard()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.framedash, homeFrag)
                commit()
            }

        }
        close.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_thankyouScreenFragment_to_vehicleNumberFragment)
        }


    }
}