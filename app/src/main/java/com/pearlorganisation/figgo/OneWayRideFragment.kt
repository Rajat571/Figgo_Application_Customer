package com.pearlorganisation.figgo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.Adapter.AdvanceCityDataAdapter
import com.pearlorganisation.figgo.Adapter.OneWayKmCountAdapter
import com.pearlorganisation.figgo.Model.AdvanceCityCabModel
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.Model.VehicleInfoList
import com.pearlorganisation.figgo.databinding.FragmentAdvanceCityCabBinding
import com.pearlorganisation.figgo.databinding.FragmentOneWayRideBinding
import org.json.JSONObject
import java.util.HashMap


class OneWayRideFragment : Fragment() {
    lateinit var oneWayKmCountAdapter: OneWayKmCountAdapter
    lateinit var binding: FragmentOneWayRideBinding
    lateinit var pref: PrefManager
    var mList= ArrayList<OneWayListRatingVehicle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getnxtbtn()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_one_way_ride, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var ll_accept =view.findViewById<LinearLayout>(R.id.ll_accept)
        val onewayvehiclelist = view.findViewById<RecyclerView>(R.id.onewayvehiclelist)
        var shareimg =view.findViewById<ImageView>(R.id.shareimg)
        var backimg =view.findViewById<ImageView>(R.id.backimg)



    }

    private fun getnxtbtn() {
        val URL = "https://test.pearl-developer.com/figo/api/ride/select-city-vehicle-type"
        val queue = Volley.newRequestQueue(requireContext())
        val json = JSONObject()
        json.put("vehicle_type_id",pref.getvehicle_type_id())
        json.put("ride_id",pref.getride_id())
        Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>
            {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {
                    Log.d("SendData", "response===" + response)
                    if (response != null) {
                        /* ll_location?.isVisible = false
                         ll_choose_vehicle?.isVisible  =true*/
                        val from_location = response.getJSONObject("data").getJSONObject("vehicle").getString("from_location")
                        val image = response.getJSONObject("data").getJSONObject("vehicle").getString("image")
                        val min_price = response.getJSONObject("data").getJSONObject("vehicle").getString("min_price")
                        val max_price = response.getJSONObject("data").getJSONObject("vehicle").getString("max_price")
                        val to_location = response.getJSONObject("data").getJSONObject("vehicle").getString("to_location")
                        val reject = response.getJSONObject("data").getJSONObject("vehicle").getString("name")
                        val distance = response.getJSONObject("data").getJSONObject("vehicle").getString("distance")
                        val size = response.getJSONObject("data").getJSONArray("vehicle_types").length()
                        val rideId = response.getJSONObject("data").getString("id")
                        for(p2 in 0 until size) {
                            val name = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("name")
                            val image = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("full_image")
                            val ride_id = response.getJSONObject("data").getJSONArray("vehicle_types").getJSONObject(p2).getString("name")
                            val vehicle_id = response.getJSONObject("data").getString("ride_id")

                            mList.add(OneWayListRatingVehicle(reject,to_location,min_price,max_price,from_location/*,image, name,distance,ride_id,from_location*/))
                        }

                        oneWayKmCountAdapter= OneWayKmCountAdapter(requireActivity(),mList)
                        binding.onewayvehiclelist.layoutManager=LinearLayoutManager(requireActivity())
                        binding.onewayvehiclelist.adapter=oneWayKmCountAdapter

                    }
                    // Get your json response and convert it to whatever you want.
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    /*Toast.makeText(this(), "Something went wrong !!", Toast.LENGTH_LONG).show()*/
                    Log.d("SendData", "error===" + error)
                    // Error
                }
            }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Authorization", "Bearer " + pref.getToken());
                    return headers
                }
            }

        queue.add(jsonOblect)

    }

}