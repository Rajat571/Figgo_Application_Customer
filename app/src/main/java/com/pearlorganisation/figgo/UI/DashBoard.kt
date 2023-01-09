package com.pearlorganisation.figgo.UI
//Neeraj

import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.pearlorganisation.figgo.Adapter.CabCategoryAdapter
import com.pearlorganisation.figgo.Adapter.FiggoAddAdapter
import com.pearlorganisation.figgo.Model.CabCategory
import com.pearlorganisation.figgo.Model.FiggoAdd
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard
import com.pearlorganisation.figgo.UI.Fragments.OutStation.RoundAndTourFragment
import com.pearlorganisation.figgo.UI.Fragments.RidesBottom
import com.pearlorganisation.figgo.UI.Fragments.SupportBottomNav
import com.pearlorganisation.figgo.databinding.ActivityDashBoardBinding
import java.util.*


class DashBoard : AppCompatActivity() {
    lateinit var binding: ActivityDashBoardBinding
    lateinit var toggle: ActionBarDrawerToggle
    private var hasGps = false
    private var hasNetwork = false
    lateinit var cabCategoryAdapter: CabCategoryAdapter
    var cab_category_list=ArrayList<CabCategory>()
    lateinit var figgoAddAdapter: FiggoAddAdapter
    var figgo_add_list=ArrayList<FiggoAdd>()
    var doubleBackToExitPressedOnce = false
    var count = 0
    private lateinit var locationRequest: LocationRequest;
    private val REQUEST_CHECK_SETTINGS: Int=101;
    var transaction_id :String ?= ""
    var backPressedTime: Long = 0
    private val permissionId = 2
    private val requestcodes = 2
    var PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=101;
    var homeFrag = HomeDashboard()
    lateinit var locationManager: LocationManager
    private var mMap: GoogleMap? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    var mLastLocation: Location? = null
    var mLocationRequest: com.google.android.gms.location.LocationRequest? = null
    private val mLogout: Button? = null
    private val mRequest: Button? = null
    private val pickupLocation: LatLng? = null
    lateinit var navView : NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.a_dashboard)
//        val recyclerView: RecyclerView = findViewById(R.id.figgo_add_list)
//        val recycler: RecyclerView = findViewById(R.id.cab_category_list)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))
        val drawerLayout = findViewById<View>(R.id.drawerLayout) as DrawerLayout

        var rides = RidesBottom()
        var more = RoundAndTourFragment()
        var support = SupportBottomNav()
        var navView = findViewById<NavigationView>(R.id.navView)


        toggle = ActionBarDrawerToggle(this@DashBoard, drawerLayout, R.string.Change_MPIN, R.string.Rides)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var layout = findViewById<ImageView>(R.id.menu_naviagtion)


        grantLocPer()



       var bottom = findViewById<BottomNavigationView>(R.id.navigation_bar)
        bottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_b->{
                    setfragment(homeFrag)
                    true

                }
                R.id.rides_b->{
                    setfragment(rides)
                    true

                }


                R.id.more_b->{
                    drawerLayout.openDrawer(Gravity.LEFT)
                  //  navView.showContextMenu()
            //setfragment(more)
            true

        }
                R.id.support_b->{
            setfragment(support)
            true


        }

                else -> {
                    setfragment(homeFrag)
                        true

                }                }
            }


//        binding.apply {
//
//        }
        /**---------------------------Cab_Category-----------------------*/

//        binding.figgoAddList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
//        figgo_add_list.add(FiggoAdd(R.drawable.figgoadd))
//        figgo_add_list.add(FiggoAdd(R.drawable.figgoadd))
//        figgo_add_list.add(FiggoAdd(R.drawable.figgoadd))
//        figgoAddAdapter=FiggoAddAdapter(figgo_add_list)
//        recycler.layoutManager = GridLayoutManager(this,3)
//        recycler.adapter=figgoAddAdapter
//        binding.figgoAddList.adapter=figgoAddAdapter
//
//
//
//        binding.cabCategoryList.layoutManager=GridLayoutManager(this,4)
//        cab_category_list.add(CabCategory(R.drawable.citycab,"City-Cab"))
//        cab_category_list.add(CabCategory(R.drawable.outstationcab,"Outstation"))
//        cab_category_list.add(CabCategory(R.drawable.fadesharecab,"Share-Cab"))
//        cab_category_list.add(CabCategory(R.drawable.airportcab,"Airpot-Cab"))
//        cab_category_list.add(CabCategory(R.drawable.royalcab,"Royal-cab"))
//        cab_category_list.add(CabCategory(R.drawable.fadetour,"Tour-plan"))
//        cab_category_list.add(CabCategory(R.drawable.goodsparcel,"Goods vechile"))
//        cab_category_list.add(CabCategory(R.drawable.fadehote,"Hotel"))
//        cab_category_list.add(CabCategory(R.drawable.fadeflight,"Flight"))
//        cab_category_list.add(CabCategory(R.drawable.fadetrain,"Train"))
//        cab_category_list.add(CabCategory(R.drawable.fadebus,"Micro Bus"))
//        cab_category_list.add(CabCategory(R.drawable.fademore,"More"))
//        cabCategoryAdapter= CabCategoryAdapter(this,cab_category_list)
//        recyclerView.layoutManager= GridLayoutManager(this,4)
//        recyclerView.adapter=cabCategoryAdapter
//        binding.cabCategoryList.adapter=cabCategoryAdapter

        /**------------------------Figgo Add---------------------------------*/


        /**--------------------------------------------------------------------------*/


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.change_mpin -> {
                    Toast.makeText(this@DashBoard, "change_mpin Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.rides -> {
                    Toast.makeText(this@DashBoard, "rides Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.Logout -> {
                    startActivity(Intent(this,LoginActivity::class.java))
                }

            }
            true
        }

    }

    private fun setfragment(frag: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framedash, frag)
            commit()
        }}



    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                // System.exit(0);
                val a = Intent(Intent.ACTION_MAIN)
                a.addCategory(Intent.CATEGORY_HOME)
                a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(a)
                finish()
                return
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)
        } else {
            supportFragmentManager.popBackStack()
        }
    }
  /*  private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = this@DashBoard.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this@DashBoard,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this@DashBoard,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this@DashBoard,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                setfragment(homeFrag)
            }
        }
    }*/

   /* override fun onResume() {
        super.onResume()
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                setfragment(homeFrag)
            } else {
                Toast.makeText(this@DashBoard, "Please turn on location", Toast.LENGTH_LONG)
                    .show()
                val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }else {
            requestPermissions()
        }
    }*/
 /*  private fun displayLocationSettingsRequest(context: Context) {
       val googleApiClient = GoogleApiClient.Builder(context)
           .addApi(LocationServices.API).build()
       googleApiClient.connect()
       val locationRequest = com.google.android.gms.location.LocationRequest.create()
       locationRequest.priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
       locationRequest.interval = 10000
       locationRequest.fastestInterval = (10000 / 2).toLong()
       val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
       builder.setAlwaysShow(true)
       val result =
           LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
       result.setResultCallback { result ->
           val status = result.status
           when (status.statusCode) {
               LocationSettingsStatusCodes.SUCCESS -> {}
               LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {}
               LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
           }
       }




   }*/




   private fun requestPermissions() {
       ActivityCompat.requestPermissions(
           this@DashBoard,
           arrayOf(
               android.Manifest.permission.ACCESS_COARSE_LOCATION,
               android.Manifest.permission.ACCESS_FINE_LOCATION
           ),
           permissionId
       )
   }
    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this@DashBoard,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@DashBoard,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@DashBoard,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                requestcodes
            )
            false
        } else {
            true
        }
    }

    fun grantLocPer() {

        if (isLocationPermissionGranted()) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        this@DashBoard,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    checkLocationService()
                } else {
                    ActivityCompat.requestPermissions(
                        this@DashBoard,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
                    );
                }
            } else {
                checkLocationService()
            }

        } else {
            requestPermissions()
        }
    }
    fun checkLocationService() {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationRequest.setFastestInterval(2 * 1000);


        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        // builder.setAlwaysShow(true);
        val client = LocationServices.getSettingsClient(this@DashBoard)
        val task = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener(this@DashBoard){it->
            it.locationSettingsStates;
           setfragment(homeFrag)
        }

        task.addOnFailureListener(this@DashBoard) { e ->
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult (
                        this@DashBoard,
                        REQUEST_CHECK_SETTINGS
                    )

                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }

            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == -1) {
           setfragment(homeFrag)
        } else {
            grantLocPer()
        }
    }

}