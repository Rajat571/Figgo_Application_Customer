package com.pearlorganisation.figgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.PrefManager
import com.pearlorganisation.figgo.Adapter.CurrentOneWayKmCountAdapter
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard
import com.pearlorganisation.figgo.databinding.ActivityOneWayKmCountBinding


class OneWay_Km_CountActivity : AppCompatActivity() {
    lateinit var binding: ActivityOneWayKmCountBinding
    lateinit var oneWayKmCountAdapter: CurrentOneWayKmCountAdapter
    lateinit var pref:PrefManager
    val mList = ArrayList<OneWayListRatingVehicle>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_way_km_count)

        var ll_accept = findViewById<LinearLayout>(R.id.ll_accept)
        val recyclerview = findViewById<RecyclerView>(R.id.onewayvehiclelist)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
       var progress = findViewById<ProgressBar>(R.id.progress)

       /*getnxtbtn()*/
        backimg.setOnClickListener {
            val intent = Intent(this, HomeDashboard::class.java)
            startActivity(intent)
        }

        shareimg.setOnClickListener {
            var intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"I am Inviting you to join  Figgo App for better experience to book cabs");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Invite Friends"));
        }

        /*mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
        mList.add(OneWayListRatingVehicle("Activa - 2012    Rs.45.00","raingcountlist","ride_service_rating","Reject","Accept"))
*/
        recyclerview.adapter = CurrentOneWayKmCountAdapter(this,mList)
        recyclerview.layoutManager = LinearLayoutManager(this)
        oneWayKmCountAdapter = CurrentOneWayKmCountAdapter(this,mList)
        recyclerview.adapter = oneWayKmCountAdapter


      /*  oneWayKmCountAdapter.onItemClick = {
            val intent = Intent(this,VehicleAboutActivity::class.java)
            *//*intent.putExtra("oneWayKmCountAdapter")*//*
            startActivity(intent)
        }*/
    }

  /*  private fun getnxtbtn() {
       *//* progress?.isVisible = true
        ll_location?.isVisible = false
        ll_choose_vehicle?.isVisible  =false*//*
        val URL = "https://test.pearl-developer.com/figo/api/ride/select-city-vehicle-type"
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("vehicle_type_id",pref.getvehicle_type_id())
        json.put("ride_id",pref.getride_id())
        Log.d("SendData", "json===" + json)
        val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
            Response.Listener<JSONObject?>               {
            override fun onResponse(response: JSONObject?) {

                Log.d("SendData", "response===" + response)
                if (response != null) {
                    val status = response.getString("status")
                    if(status.equals("false")){
                        Toast.makeText(this@OneWay_Km_CountActivity, "Something Went Wrong!", Toast.LENGTH_LONG).show()
                    }else{
                        val data = response.getJSONObject("data")
                        val ride_id = data.getString("ride_id")
                        val vehicle_types = data.getJSONArray("vehicle_types")
                        for (i in 0 until vehicle_types.length()){

                            val name = vehicle_types.getJSONObject(i).getString("name")
                            val image = vehicle_types.getJSONObject(i).getString("full_image")
                            val id = vehicle_types.getJSONObject(i).getString("id")
                            val min_price = vehicle_types.getJSONObject(i).getString("min_price")
                            val max_price = vehicle_types.getJSONObject(i).getString("max_price")

                          *//*  mList.add(OneWayListRatingVehicle(name,image,id,ride_id,min_price, max_price))*//*

                        }
                        oneWayKmCountAdapter= OneWayKmCountAdapter(this@OneWay_Km_CountActivity,mList)
                       binding.onewayvehiclelist.adapter=oneWayKmCountAdapter
                       binding.onewayvehiclelist.layoutManager= LinearLayoutManager(this@OneWay_Km_CountActivity)
                        *//*progress?.isVisible = false
                        ll_location?.isVisible = false
                        ll_choose_vehicle?.isVisible  =true*//*
                    }


                    }
                    // Get your json response and convert it to whatever you want.
                }
            }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.d("SendData", "error===" + error)
                Toast.makeText(this@OneWay_Km_CountActivity, "Something Went Wrong!", Toast.LENGTH_LONG).show()
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

        jsonOblect.setRetryPolicy(DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))
        queue.add(jsonOblect)

    }*/

}






