package com.pearlorganisation.figgo.UI

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.pearlorganisation.figgo.Adapter.CabCategoryAdapter
import com.pearlorganisation.figgo.Adapter.FiggoAddAdapter
import com.pearlorganisation.figgo.Model.CabCategory
import com.pearlorganisation.figgo.Model.FiggoAdd
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.UI.Fragments.HomeDashboard
import com.pearlorganisation.figgo.UI.Fragments.OutStation.FragmentOneWay
import com.pearlorganisation.figgo.UI.Fragments.OutStation.PackageFragment
import com.pearlorganisation.figgo.UI.Fragments.OutStation.RoundAndTourFragment
import com.pearlorganisation.figgo.UI.Fragments.RidesBottom
import com.pearlorganisation.figgo.UI.Fragments.SupportBottomNav
import com.pearlorganisation.figgo.databinding.ActivityDashBoardBinding
import com.pearlorganisation.figgo.databinding.FragmentRidesBottomBinding


class DashBoard : AppCompatActivity(){
    lateinit var binding: ActivityDashBoardBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navView : NavigationView
    lateinit var cabCategoryAdapter: CabCategoryAdapter
    var cab_category_list=ArrayList<CabCategory>()
    lateinit var figgoAddAdapter: FiggoAddAdapter
    var figgo_add_list=ArrayList<FiggoAdd>()
    var doubleBackToExitPressedOnce = false
    var count = 0
    var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.a_dashboard)
//        val recyclerView: RecyclerView = findViewById(R.id.figgo_add_list)
//        val recycler: RecyclerView = findViewById(R.id.cab_category_list)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))
        val drawerLayout = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        var homeFrag = HomeDashboard()
        var rides = RidesBottom()
        var more = RoundAndTourFragment()
        var support = SupportBottomNav()
        var navView = findViewById<NavigationView>(R.id.navView)


        toggle = ActionBarDrawerToggle(this@DashBoard, drawerLayout, R.string.Change_MPIN, R.string.Rides)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var layout = findViewById<ImageView>(R.id.menu_naviagtion)
        layout.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        setfragment(homeFrag)
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
}