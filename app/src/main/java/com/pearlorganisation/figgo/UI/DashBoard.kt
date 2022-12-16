package com.pearlorganisation.figgo.UI

import android.Manifest
import android.graphics.Color
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pearlorganisation.figgo.Adapter.CabCategoryAdapter
import com.pearlorganisation.figgo.Adapter.FiggoAddAdapter
import com.pearlorganisation.figgo.Model.CabCategory
import com.pearlorganisation.figgo.Model.FiggoAdd
import com.pearlorganisation.figgo.R
import com.pearlorganisation.figgo.databinding.ActivityDashBoardBinding

class DashBoard : AppCompatActivity(){
    lateinit var binding: ActivityDashBoardBinding
    lateinit var cabCategoryAdapter: CabCategoryAdapter
    var cab_category_list=ArrayList<CabCategory>()
    lateinit var figgoAddAdapter: FiggoAddAdapter
    var figgo_add_list=ArrayList<FiggoAdd>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding=DataBindingUtil.setContentView(this,R.layout.activity_dash_board)
        var window=window
        window.setStatusBarColor(Color.parseColor("#000F3B"))

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

    }
}