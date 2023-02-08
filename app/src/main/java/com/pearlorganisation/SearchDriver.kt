package com.pearlorganisation

import android.annotation.SuppressLint
import android.app.*
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.view.isVisible
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.PaymentMethodActivity
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UTIL.MapUtility
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONException
import org.json.JSONObject


class SearchDriver : BaseClass() , PaymentResultListener {
    lateinit var pref: PrefManager
    lateinit var progressDialog:ProgressDialog
    lateinit var cTimer : CountDownTimer
    var count :Int = 0
    var txtTimer: TextView? = null
    var transaction_id :String ?= ""
    var  driver_id:String? = null
    var  ride_id:String? = null
    var toLat:String = ""
    lateinit var driverlayout:ConstraintLayout
    var v_number:String = ""
    var prices:String = ""
    var name:String = ""
    var dlnumber:String = ""
    var activavehiclenumber:TextView? = null
    var dl_number:TextView? = null
    var drivername:TextView? = null
    var price:TextView? = null
    var textView:TextView? = null
    var mobilenumber:TextView? = null
    var activaimg:ImageView? = null
    var  driverimg:ImageView? = null
    var ll_search:LinearLayout? = null
    var  ll_details:LinearLayout? = null
    var x:Int=-1

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Driver Found"
    var paymentMethodActivity = PaymentMethodActivity()
    override fun setLayoutXml() {
        TODO("Not yet implemented")
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeClickListners() {
        TODO("Not yet implemented")
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }




    override fun initializeLabels() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_driver)
        pref = PrefManager(this@SearchDriver)
//        var tv_click = findViewById<TextView>(R.id.tv_click)
        driverlayout  = findViewById<ConstraintLayout>(R.id.layoutdriverdetails)
         txtTimer = findViewById<TextView>(R.id.txt_timer)
        var tv_accept = findViewById<TextView>(R.id.tv_accept)
        var shareimg = findViewById<ImageView>(R.id.shareimg)
        var backimg = findViewById<ImageView>(R.id.backimg)
         activaimg = findViewById<ImageView>(R.id.activaimg)
         activavehiclenumber = findViewById<TextView>(R.id.activavehiclenumber)
         driverimg = findViewById<CircleImageView>(R.id.driverimg)
         drivername = findViewById<TextView>(R.id.drivername)
         price = findViewById<TextView>(R.id.price)
        textView = findViewById<TextView>(R.id.textView)
        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)
        var ride_service_rating = findViewById<RatingBar>(R.id.ride_service_rating)
         dl_number = findViewById<TextView>(R.id.dl_number)
        ll_search = findViewById<LinearLayout>(R.id.ll_main)
        ll_details = findViewById<LinearLayout>(R.id.ll_details)

        driver_id = intent.getStringExtra("driver_id")
        ride_id = intent.getStringExtra("ride_id")
        pref = PrefManager(this)

        iv_bellicon.setOnClickListener {
            startActivity(Intent(this,NotificationBellIconActivity::class.java))
        }

        /* getcurrentdriverdetails()*/

        tv_accept.setOnClickListener {
            pref.setSearchBack("")

            val amt = pref.getPrice()
            val amount = Math.round(amt.toFloat() * 100).toInt()
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_capDM1KlnUhj5f")
            checkout.setImage(R.drawable.appicon)
            val obj = JSONObject()
            try {
                obj.put("name", "Figgo")
                obj.put("description", "Payment")
                obj.put("theme.color", "")
                obj.put("send_sms_hash", true)
                obj.put("allow_rotation", true)
                obj.put("currency", "INR")
                obj.put("amount", amount)
                val preFill = JSONObject()
                preFill.put("email", "support@figgocabs.com")
                preFill.put("contact", "91" + "9715597855")
                obj.put("prefill", preFill)
                checkout.open(this, obj)
            } catch (e: JSONException) {
                Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_SHORT).show();
                e.printStackTrace()
            }
        }
        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }

        pref.setSearchBack("1")
        shareimg()
        onBackPress()
        searchDriver()


    }
    @SuppressLint("MissingInflatedId")
    private fun showPendingPopup(view: View) {
        //Create a View object yourself through inflater
        val inflater = view.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.row_pending_layout, null)





        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.MATCH_PARENT

        //Make Inactive Items Outside Of PopupWindow
        val focusable = true

        //Create a window with our parameters
        val popupWindow = PopupWindow(popupView, width, height, focusable)

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
        popupWindow.isOutsideTouchable = false
        val container = popupWindow.contentView.parent as View
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = container.layoutParams as WindowManager.LayoutParams
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount = 0.4f
        wm.updateViewLayout(container, p)
        val txt_msg = popupView.findViewById<TextView>(R.id.txt_msg)
        txt_msg.setText("Dear Sir/Ma'am , your ride fare is Rs "+pref.getPrice()+" and pick up time is "+pref.getTime()+" . If 100% sure for this ride then accept or ortherwise reject. Charges Apply after 5 Min delay")
        val imageView: ImageView = popupView.findViewById(R.id.iv_close)
        imageView.setOnClickListener { popupWindow.dismiss() }
    }
    private fun searchDriver() {
       // progressDialog = ProgressDialog(this@SearchDriver)
      //  progressDialog.show()
        val URL = "https://test.pearl-developer.com/figo/api/ride/searching-driver"
        Log.d("searchDriver", "json===" +URL )
        val queue = Volley.newRequestQueue(this@SearchDriver)
        val json = JSONObject()
        json.put("ride_id" ,pref.getride_id())
        Log.d("SendData", pref.getride_id())
        val jsonOblect: JsonObjectRequest =  object : JsonObjectRequest(Method.POST, URL, json, object : Response.Listener<JSONObject?> {
            override fun onResponse(response: JSONObject?) {
                Log.d("SendData", "response===" + response)
                if (response != null) {
                  //  progressDialog.hide()
                    try {
                        val searching_status = response.getString("searching_status")
                        /*Log.d("SendData", "searching_status===" + searching_status)*/
                        if (searching_status.equals("0")) {

                            Toast.makeText(this@SearchDriver, "Unable to find driver...", Toast.LENGTH_LONG).show()

                        } else if (searching_status.equals("1")) {
                            val intent = Intent(this@SearchDriver, SearchDriver::class.java)

                            // FLAG_UPDATE_CURRENT specifies that if a previous
                            // PendingIntent already exists, then the current one
                            // will update it with the latest intent
                            // 0 is the request code, using it later with the
                            // same method again will get back the same pending
                            // intent for future reference
                            // intent passed here is to our afterNotification class
                            val pendingIntent = PendingIntent.getActivity(this@SearchDriver, 0, intent,  PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

                            // RemoteViews are used to use the content of
                            // some different layout apart from the current activity layout
                           // val contentView = RemoteViews(packageName, R.layout.activity_after_notification)
                            // RemoteViews are used to use the content of
                            // some different layout apart from the current activity layout


                            addNotification()

                            getRideStatus()


                        }
                    }catch (e:Exception){
                        MapUtility.showDialog(e.toString(),this@SearchDriver)

                    }
                } else{
                    Toast.makeText(this@SearchDriver, "Unable to search driver...", Toast.LENGTH_LONG).show()
                }
            }
        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {

               // progressDialog.hide()
                MapUtility.showDialog(error.toString(),this@SearchDriver)

                /* Toast.makeText(context, "Something went wrong!"+error, Toast.LENGTH_LONG).show()*/
            }
        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers.put("Content-Type", "application/json; charset=UTF-8")
                headers.put("Authorization", "Bearer " + pref.getToken())
                headers.put("Accept", "application/vnd.api+json") //put your token here
                return headers
            }
        }

        queue.add(jsonOblect)

    }
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun addNotification() {
        val mNotificationManager: NotificationManager
        val mBuilder = NotificationCompat.Builder(this@SearchDriver, "notify_001")
        val ii = Intent(this@SearchDriver, SearchDriver::class.java)
        val pendingIntent = PendingIntent.getActivity(this@SearchDriver, 0, ii, 0)

        val bigText = NotificationCompat.BigTextStyle()
        bigText.bigText("Hello")
        bigText.setBigContentTitle("Today's Bible Verse")
        bigText.setSummaryText("Text in detail")

        mBuilder.setContentIntent(pendingIntent)
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
        mBuilder.setContentTitle("Your Title")
        mBuilder.setContentText("Your text")
        mBuilder.priority = Notification.PRIORITY_MAX
        mBuilder.setStyle(bigText)

        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "Your_channel_id"
            val channel = NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_HIGH)
            mNotificationManager.createNotificationChannel(channel)
            mBuilder.setChannelId(channelId)
        }

        mNotificationManager.notify(0, mBuilder.build())
    }
    protected fun displayNotification() {
        Log.i("Start", "notification")

        /* Invoking the default notification service */
        val mBuilder = NotificationCompat.Builder(this)
        mBuilder.setContentTitle("New Message")
        mBuilder.setContentText("You've received new message.")
        mBuilder.setTicker("New Message Alert!")
        mBuilder.setSmallIcon(R.drawable.appicon)

        /* Increase notification number every time a new notification arrives */
        //mBuilder.setNumber(
       //     ++numMessages
      //  )

        /* Add Big View Specific Configuration */
        val inboxStyle = NotificationCompat.InboxStyle()
        val events = arrayOfNulls<String>(6)
        events[0] = String("This is first line....")
        events[1] = String("This is second line...")
        events[2] = String("This is third line...")
        events[3] = String("This is 4th line...")
        events[4] = String("This is 5th line...")
        events[5] = String("This is 6th line...")

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Big Title Details:")

        // Moves events into the big view
        for (i in events.indices) {
            inboxStyle.addLine(events[i])
        }
        mBuilder.setStyle(inboxStyle)

        /* Creates an explicit intent for an Activity in your app */
        val resultIntent = Intent(this, SearchDriver::class.java)

        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(SearchDriver::class.java)

        /* Adds the Intent that starts the Activity to the top of the stack */stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        mBuilder.setContentIntent(resultPendingIntent)
       var mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(1, mBuilder.build())
    }

    private fun String(s: String): String? {

        return s
    }

    private fun getRideStatus() {
        val URL ="https://test.pearl-developer.com/figo/api/ride/check-ride-request-status"
        Log.d("searchDriver", "json===" +URL )
        Log.d("SendData", pref.getride_id() )
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("ride_id", pref.getride_id())
       //json.put("ride_id", "1070")
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json,
                Response.Listener<JSONObject?> { response ->
                    if (response != null) {
                        try {

                            driverlayout.visibility = View.VISIBLE
                            x = 1
                            Log.d("Ride Status", "response===" + response)

                            val status = response.getString("status")

                            if (status.equals("true")) {
                                if (::cTimer.isInitialized) {
                                    cTimer.cancel()
                                }
                                ll_search?.isVisible = false
                                ll_details?.isVisible = true
                                prices = response.getJSONObject("data").getString("price")
                                name = response.getJSONObject("data").getJSONObject("ride_driver")
                                    .getString("name")
                                dlnumber =
                                    response.getJSONObject("data").getJSONObject("ride_driver")
                                        .getString("dl_number")
                                v_number =
                                    response.getJSONObject("data").getJSONObject("ride_driver")
                                        .getJSONObject("cab").getString("v_number")
                                /*val name1 = response.getJSONObject("data").getJSONObject("cab_details").getString("name")*/
                                pref.setReqRideId(response.getJSONObject("data").getString("id"))
                                val rating_avg = response.getJSONObject("data").getJSONObject("ride_driver").getString("rating_avg")

                                drivername?.setText(name)
                                dl_number?.setText(dlnumber)
                                activavehiclenumber?.setText(v_number)
                                price?.setText(prices)
                                pref.setPrice(prices)
                                /*   if(!full_image.equals("")){
                                Picasso.get().load(full_image).placeholder(R.drawable.girl_img).into(driverimg)
                            }*/

                                /* if(!taxi_image.equals("")){
                                Picasso.get().load(taxi_image).placeholder(R.drawable.blueactiva_img).into(activaimg)
                            }*/


                            } else if (status.equals("false")) {
                                //  Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show()

                                startTimer()

                            }

                        }catch (e:Exception){
                            MapUtility.showDialog(e.toString(),this@SearchDriver)

                        }
                    }
                }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Log.d("SendData", "error===" + error)
                    //

                    MapUtility.showDialog(error.toString(),this@SearchDriver)
                }
            }) {

                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = java.util.HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Authorization", "Bearer " + pref.getToken());
                    headers.put("Accept", "application/vnd.api+json");
                    return headers
                }
            }

        queue.add(jsonOblect)

    }
    override fun onPaymentSuccess(s: String?) {
        Toast.makeText(this, "payment successful", Toast.LENGTH_SHORT).show()

        try {
            transaction_id = s
            getOtp()
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception in onPaymentSuccess", e)
        }

    }

    override fun onPaymentError(i: Int, s: String?) {
        Toast.makeText(this, "Payment failed$i", Toast.LENGTH_SHORT).show()

    }



    fun startTimer() {

        cTimer = object : CountDownTimer(300000, 1000) {
            override fun onTick(millisUntilFinished: Long) {//300000
                txtTimer?.setText("seconds remaining: " +""+ (millisUntilFinished / 1000).toString())
                if (count % 10 ==  0) {
                    getRideStatus()
                   // Toast.makeText(this@SearchDriver, "fetching driver...", Toast.LENGTH_LONG).show()

                }
                count++
            }

            override fun onFinish() {
                Toast.makeText(this@SearchDriver, "Unable to found nearby drivers...", Toast.LENGTH_LONG).show()
                textView?.setText("Unable to found nearby drivers...")



                /* deletePendingReq()*/
                finish()

            }
        }
        cTimer.start()
    }
    private fun getUpdatePayment() {
      //  val progressDialog = ProgressDialog(this)
      //  progressDialog.show()
        val URL ="https://test.pearl-developer.com/figo/api/ride/update-city-ride-payment-status"
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("transaction_id", transaction_id.toString())
        json.put("payment_type", "card")
        json.put("ride_id", pref.getRideId())
        Log.d("transac",transaction_id.toString())
        Log.d("rides",pref.getride_id())
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>               {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {

                    Log.d("SendData", "response===" + response)
                    if (response != null) {
                      //  progressDialog.hide()
                        try {

                            val status = response.getString("status")
                            if (status.equals("true")) {
                                getAcceptRide()
                            } else {

                            }
                        }catch (e:Exception){
                            MapUtility.showDialog(e.toString(),this@SearchDriver)

                        }
                    }

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                  //  progressDialog.hide()
                    Log.d("SendDataE", "error===" + error)
                    // Toast.makeText(this@Current_Driver_Details_List, "Something went wrong!", Toast.LENGTH_LONG).show()


                    MapUtility.showDialog(error.toString(),this@SearchDriver)
                }
            }) {



                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = java.util.HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Authorization", "Bearer " + pref.getToken());
                    headers.put("Accept", "application/vnd.api+json");
                    return headers
                }
            }

        queue.add(jsonOblect)

    }


    private fun getAcceptRide() {
       // val progressDialog = ProgressDialog(this)
      //  progressDialog.show()
        val URL ="https://test.pearl-developer.com/figo/api/ride/accept-current-city-ride"
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("ride_request_id", pref.getReqRideId())
        json.put("ride_id", pref.getride_id())
      //  Log.d("transac",transaction_id.toString())
      //  Log.d("rides",pref.getride_id())
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>               {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {

                    Log.d("SendData", "response===" + response)
                    if (response != null) {
                        try {

                            val status = response.getString("status")
                            if (status.equals("true")) {
                                val intent = Intent(this@SearchDriver, EmergencyRoutedraweActivity::class.java)
                                intent.putExtra("name", name)
                                intent.putExtra("dl_number", dlnumber)
                                intent.putExtra("veh_number", v_number)
                                intent.putExtra("price", prices)

                                startActivity(Intent(this@SearchDriver,EmergencyRoutedraweActivity::class.java))

                            } else {

                            }
                        }catch (e:Exception){
                            MapUtility.showDialog(e.toString(),this@SearchDriver)

                        }
                    }

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                   // progressDialog.hide()
                    Log.d("SendData", "error===" + error)
                    // Toast.makeText(this@Current_Driver_Details_List, "Something went wrong!", Toast.LENGTH_LONG).show()

                    MapUtility.showDialog(error.toString(),this@SearchDriver)
                }
            }) {

                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = java.util.HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Authorization", "Bearer " + pref.getToken());
                    headers.put("Accept", "application/vnd.api+json");
                    return headers
                }
            }

        queue.add(jsonOblect)

    }




    private fun getOtp() {
        val progressDialog = ProgressDialog(this)
        progressDialog.show()
        val URL ="https://test.pearl-developer.com/figo/api/ride/update-city-ride-payment-status"
        val queue = Volley.newRequestQueue(this)
        val json = JSONObject()
        json.put("transaction_id", transaction_id.toString())
        json.put("payment_type", "card")
        json.put("ride_id", pref.getRideId())
        Log.d("transac",transaction_id.toString())
        Log.d("rides",pref.getride_id())
        val jsonOblect: JsonObjectRequest =
            object : JsonObjectRequest(Method.POST, URL, json, object :
                Response.Listener<JSONObject?>               {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(response: JSONObject?) {

                    Log.d("SendData", "response===" + response)
                    if (response != null) {
                        progressDialog.hide()
                        try {

                            val booking_no = response.getJSONObject("ride").getString("booking_id")
                            val otp = response.getInt("otp")

                            pref.setOtp(otp.toString())
                            pref.setBookingNo(booking_no)

                            getUpdatePayment()
                        }catch (e:Exception){
                            MapUtility.showDialog(e.toString(),this@SearchDriver)

                        }
                    }

                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    progressDialog.hide()
                    Log.d("SendData", "error===" + error)
                    // Toast.makeText(this@Current_Driver_Details_List, "Something went wrong!", Toast.LENGTH_LONG).show()

                    MapUtility.showDialog(error.toString(),this@SearchDriver)
                }
            }) {

                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = java.util.HashMap()
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Authorization", "Bearer " + pref.getToken());
                    headers.put("Accept", "application/vnd.api+json");
                    return headers
                }
            }

        queue.add(jsonOblect)

    }
    override fun onBackPressed() {

        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.serach_driver_dialog)
        val body = dialog.findViewById(R.id.error) as TextView

        val yesBtn = dialog.findViewById(R.id.ok) as Button
        val canBtn = dialog.findViewById(R.id.cancel) as Button
        yesBtn.setOnClickListener {
            pref.setSearchBack("")
            finish()
        }
        canBtn.setOnClickListener {
            dialog.dismiss()
        }
        if (!(this@SearchDriver as Activity).isFinishing) {
            dialog.show()
        }

        val window: Window? = dialog.getWindow()
        window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }



}