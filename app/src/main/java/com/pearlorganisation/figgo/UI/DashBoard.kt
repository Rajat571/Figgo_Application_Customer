package com.pearlorganisation.figgo.UI

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.pearlorganisation.figgo.Adapter.CabCategoryAdapter
import com.pearlorganisation.figgo.Adapter.FiggoAddAdapter
import com.pearlorganisation.figgo.Model.CabCategory
import com.pearlorganisation.figgo.Model.FiggoAdd
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityDashBoardBinding


class DashBoard : AppCompatActivity(){
    lateinit var binding: ActivityDashBoardBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navView : NavigationView
    lateinit var cabCategoryAdapter: CabCategoryAdapter
    var cab_category_list=ArrayList<CabCategory>()
    lateinit var figgoAddAdapter: FiggoAddAdapter
    var figgo_add_list=ArrayList<FiggoAdd>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.a_dashboard)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))
        val drawerLayout = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        var navView = findViewById<NavigationView>(R.id.navView)


        binding.apply {

        }
        /**---------------------------Cab_Category-----------------------*/

     binding.cabCategoryList.layoutManager=GridLayoutManager(this,4)
        cab_category_list.add(CabCategory(R.drawable.citycab,"City-Cab"))
        cab_category_list.add(CabCategory(R.drawable.outstationcab,"Outstation"))
        cab_category_list.add(CabCategory(R.drawable.fadesharecab,"Share-Cab"))
        cab_category_list.add(CabCategory(R.drawable.airportcab,"Airpot-Cab"))
        cab_category_list.add(CabCategory(R.drawable.royalcab,"Royal-cab"))
        cab_category_list.add(CabCategory(R.drawable.fadetour,"Tour-plan"))
        cab_category_list.add(CabCategory(R.drawable.goodsparcel,"Goods vechile"))
        cab_category_list.add(CabCategory(R.drawable.fadehote,"Hotel"))
        cab_category_list.add(CabCategory(R.drawable.fadeflight,"Flight"))
        cab_category_list.add(CabCategory(R.drawable.fadetrain,"Train"))
        cab_category_list.add(CabCategory(R.drawable.fadebus,"Micro Bus"))
        cab_category_list.add(CabCategory(R.drawable.fademore,"More"))
        cabCategoryAdapter= CabCategoryAdapter(this,cab_category_list)
        binding.cabCategoryList.adapter=cabCategoryAdapter

        /**------------------------Figgo Add---------------------------------*/

        binding.figgoAddList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        figgo_add_list.add(FiggoAdd(R.drawable.figgoadd))
        figgo_add_list.add(FiggoAdd(R.drawable.figgoadd))
        figgo_add_list.add(FiggoAdd(R.drawable.figgoadd))
        figgoAddAdapter=FiggoAddAdapter(figgo_add_list)
        binding.figgoAddList.adapter=figgoAddAdapter

        /**--------------------------------------------------------------------------*/
        toggle = ActionBarDrawerToggle(this@DashBoard, drawerLayout, R.string.Change_MPIN, R.string.Rides)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

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


}