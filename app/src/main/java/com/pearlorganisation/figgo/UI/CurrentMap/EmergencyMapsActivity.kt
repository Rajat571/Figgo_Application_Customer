package com.pearlorganisation.figgo.UI.CurrentMap

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.pearlorganisation.figgo.UI.Current_Driver_Details_List
import com.pearlorganisation.figgo.pearlLib.PrefManager
import com.pearlorganisation.figgo.UI.DriveRatingActivity
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityEmergencyMapsBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class EmergencyMapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    private var originLatitude: Double = 30.287958
    private var originLongitude: Double = 77.998449
    private var destinationLatitude: Double = 30.287958
    private var destinationLongitude: Double = 77.998449
    lateinit var pref: PrefManager
    var toLat:String = ""
    var toLong:String = ""
    var fromLat:String = ""
    var fromLong:String = ""
    var pickupLocation: LatLng? = null
    var dropLocation: LatLng? = null
    var pontos: List<LatLng> = java.util.ArrayList()
    var polyline: Polyline? = null
    lateinit var cTimer : CountDownTimer
    var  kmsTxt:TextView? = null
    var  txtTimer:TextView? = null
    var distanceString = ""

    private lateinit var binding: ActivityEmergencyMapsBinding
    lateinit var tv_dl_number: TextView
    lateinit var iv_drivername: TextView
    lateinit var tv_vehiclenumber: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pref = PrefManager(this)
        binding = ActivityEmergencyMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var emrgencybtn = findViewById<TextView>(R.id.emrgencybtn)
       /* tv_vehiclenumber = findViewById<TextView>(R.id.tv_vehiclenumber)
        iv_drivername = findViewById<TextView>(R.id.iv_drivername)
        tv_dl_number = findViewById<TextView>(R.id.tv_dl_number)*/
        var tv_emrgencybtn = findViewById<TextView>(R.id.tv_emrgencybtn)
        var tv_otp = findViewById<TextView>(R.id.tv_otp)
        var ll_back = findViewById<LinearLayout>(R.id.ll_back)
        var shareimg = findViewById<ImageView>(R.id.shareimg)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        /*shareimg()
        onBackPress()*/


        /* activanumber.setText(pref.getactivavehiclenumber())
        driver_name.setText(pref.getdrivername())
        dlnumber.setText(pref.getdl_number())*/




        pref.getactivavehiclenumber().toString()
        pref.getdrivername().toString()
        pref.getdl_number().toString()
        val bundle = intent.extras
        if (bundle != null) {

            /* driver_name.text = " ${bundle.getString("drivername")}"
            activanumber.text = "${bundle.getString("activavehiclenumber")}"
            dlnumber.text = "${bundle.getString("dl_number")}"*/
        }

        ll_back.setOnClickListener {
            startActivity(Intent(this, Current_Driver_Details_List::class.java))
        }
        shareimg.setOnClickListener {

        }



        tv_emrgencybtn.setOnClickListener {
            startActivity(Intent(this, DriveRatingActivity::class.java))


        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

    fun startTimer() {

        cTimer = object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {//300000
                txtTimer?.setText("seconds remaining: " +""+ (millisUntilFinished / 1000).toString())
            }

            override fun onFinish() {
               /* deletePendingReq()*/

            }
        }
        cTimer.start()
    }
    fun startTimer1() {

        cTimer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {//120000

            }

            override fun onFinish() {

               /* getAccept()*/

            }
        }
        cTimer.start()
    }

    inner class GetDirection :
        AsyncTask<String?, String?, String?>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        protected override fun doInBackground(vararg params: String?): String? {
            var stringUrl = ""
            stringUrl = "https://maps.googleapis.com/maps/api/directions/json?origin=" + params[0] + "&destination=" + params[1] + "&key=" + "AIzaSyCbd3JqvfSx0p74kYfhRTXE7LZghirSDoU" + "&sensor=false"
            Log.e("URL : ", "" + stringUrl)
            val response = StringBuilder()
            try {
                val url = URL(stringUrl)
                val httpconn = url.openConnection() as HttpURLConnection
                if (httpconn.responseCode == HttpURLConnection.HTTP_OK) {
                    val input = BufferedReader(InputStreamReader(httpconn.inputStream), 8192)
                    var strLine: String? = null
                    while (input.readLine().also { strLine = it } != null) {
                        response.append(strLine)
                    }
                    input.close()
                }
                val jsonOutput = response.toString()
                val jsonObject = JSONObject(jsonOutput)
                val routesArray = jsonObject.getJSONArray("routes")
                val route = routesArray.getJSONObject(0)
                val poly = route.getJSONObject("overview_polyline")
                val polyline = poly.getString("points")
                pontos = decodePoly(polyline)!!
                val legs = route.getJSONArray("legs")
                var steps: JSONObject
                var distance: JSONObject? = null
                var totalDistance = 0f
                for (i in 0 until legs.length()) {
                    steps = legs.getJSONObject(i)
                    distance = steps.getJSONObject("distance")
                    val total = distance.getString("text").split(" ".toRegex()).toTypedArray()
                    totalDistance += total[0].replace(",", "").toFloat()
                }
                distanceString = "$totalDistance Km"
                Log.e("Total Distance : ", "" + distanceString)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        private fun decodePoly(encoded: String): List<LatLng>? {
            val poly: MutableList<LatLng> = ArrayList()
            var index = 0
            val len = encoded.length
            var lat = 0
            var lng = 0
            while (index < len) {
                var b: Int
                var shift = 0
                var result = 0
                do {
                    b = encoded[index++].code - 63
                    result = result or (b and 0x1f shl shift)
                    shift += 5
                } while (b >= 0x20)
                val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
                lat += dlat
                shift = 0
                result = 0
                do {
                    b = encoded[index++].code - 63
                    result = result or (b and 0x1f shl shift)
                    shift += 5
                } while (b >= 0x20)
                val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
                lng += dlng
                val p = LatLng(lat.toDouble() / 1E5, lng.toDouble() / 1E5)
                poly.add(p)
            }
            return poly
        }



    }
    }





