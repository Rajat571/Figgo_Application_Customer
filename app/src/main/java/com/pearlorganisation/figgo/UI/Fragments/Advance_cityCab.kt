package com.pearlorganisation.figgo.UI.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.pearlorganisation.figgo.Adapter.AdvanceCityAdapter
import com.pearlorganisation.figgo.Model.AdvanceCityCab
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.FragmentAdvanceCityCabBinding
import java.text.SimpleDateFormat
import java.util.*


class Advance_cityCab : Fragment() {

    lateinit var binding: FragmentAdvanceCityCabBinding
    lateinit var advanceCityAdapter: AdvanceCityAdapter
    var cablist=ArrayList<AdvanceCityCab>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_advance_city_cab, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var calenderimg = view.findViewById<ImageView>(R.id.calenderimg)
        var datetext = view.findViewById<TextView>(R.id.datetext)
        var watchimg = view?.findViewById<ImageView>(R.id.watchimg)
        var timetext = view?.findViewById<TextView>(R.id.timetext)
        calenderimg.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { view, year, monthOfYear, dayOfMonth ->
                        val dat : String
                        if (monthOfYear < 9){
                            dat = (dayOfMonth.toString() + "-0" + (monthOfYear + 1) + "-" + year)
                        }else {
                             dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        }
                        datetext.setText(dat)
                    },
                    year,
                    month,
                    day
                )
            }

            if (datePickerDialog != null) {
                datePickerDialog.show()
            }
            watchimg?.setOnClickListener {
                val cal = Calendar.getInstance()
                val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    if (timetext != null) {
                        timetext.text = SimpleDateFormat("HH:mm").format(cal.time)
                    }
                }
                TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
            }


        }

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
