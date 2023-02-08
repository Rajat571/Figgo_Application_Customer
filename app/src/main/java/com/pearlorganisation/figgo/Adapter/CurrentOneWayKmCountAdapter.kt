package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.pearlLib.PrefManager
import com.pearlorganisation.figgo.UI.Current_Cab_DetailsActivity
import com.pearlorganisation.figgo.Model.OneWayListRatingVehicle
import com.pearlorganisation.figgo.R

class CurrentOneWayKmCountAdapter(var context: Context, private val mList: List<OneWayListRatingVehicle>) : RecyclerView.Adapter<CurrentOneWayKmCountAdapter.ViewHolder>() {

    lateinit var pref: PrefManager
    
   /* var onItemClick : ((OneWayListRatingVehicle) -> Unit)? = null*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentOneWayKmCountAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.oneway_count_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrentOneWayKmCountAdapter.ViewHolder, position: Int) {
        pref = PrefManager(context)
        val OneWayListRatingVehicle = mList[position]

        holder.vehicleprice.text = "Rs. "+mList.get(position).price
        holder.vehiclname.text = mList.get(position).name+" "+mList.get(position).year
        val rating = mList.get(position).rating
        holder.rating.text = rating
        holder.ride_service_rating.rating = rating.toFloat()



        holder.ll_main.setOnClickListener {
            context.startActivity(Intent(context, Current_Cab_DetailsActivity::class.java)
                .putExtra("ride_id",OneWayListRatingVehicle.ride_id)
                .putExtra("driver_id",OneWayListRatingVehicle.driver_id))
        }

       /* holder.acceptbutton.setOnClickListener {
           // Toast.makeText(context,"Accepted Driver",Toast.LENGTH_SHORT).show()

            val URL = "https://test.pearl-developer.com/figo/api/ride/select-driver"
           // Log.d("SendData", "URL===" + URL)
            val queue = Volley.newRequestQueue(context)
            val json = JSONObject()
            json.put("ride_id",pref.getRideId())
            json.put("driver_id",pref.getdriver_id())
            json.put("price",pref.getprice())
            Log.d("SendData", "pref.getToken()===" + pref.getToken())
            Log.d("SendData", "json===" + json)
            val jsonOblect: JsonObjectRequest = object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>               {
                override fun onResponse(response: JSONObject?) {
                    Log.d("SendData", "response===" + response)
                    if (response != null) {
                        val status = response.getString("status")
                        if(status.equals("false")){
                            Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show()
                        }else{

                            val bundle = Bundle()
                            bundle.putString("drivername", "test driver")
                            bundle.putString("activavehiclenumber", "test vehicle number")
                            bundle.putString("dl_number", "test dlnumber")
                            val intent = Intent(context, EmergencyMapsActivity::class.java)
                            intent.putExtras(bundle)
                            context.startActivity(intent)

                        }
                    }

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("SendData", "error===" + error)
                    Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show()
                }
            }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = java.util.HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8")
                    headers.put("Authorization", "Bearer " + pref.getToken())
                    headers.put("Accept", "application/vnd.api+json");
                    return headers
                }
            }
            jsonOblect.setRetryPolicy(DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))

            queue.add(jsonOblect)


        context.startActivity(Intent(context, EmergencyMapsActivity::class.java))


        }

        holder.reject.setOnClickListener {
           Toast.makeText(context,"Rejected",Toast.LENGTH_SHORT).show()
        }*/

        pref.setRideId(OneWayListRatingVehicle.ride_id)
        pref.setdriver_id(OneWayListRatingVehicle.driver_id)
        pref.setprice(OneWayListRatingVehicle.price)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        var vehiclname:TextView = itemView.findViewById(R.id.vehiclname)
        var vehicleprice:TextView = itemView.findViewById(R.id.vehicleprice)
        var acceptbutton:TextView = itemView.findViewById(R.id.acceptbutton)
        var ride_service_rating:RatingBar = itemView.findViewById(R.id.ride_service_rating)
        var reject:TextView = itemView.findViewById(R.id.reject)
        val rating:TextView = itemView.findViewById(R.id.rating)
        val ll_main:LinearLayout = itemView.findViewById(R.id.ll_main)

    }

}