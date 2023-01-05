package com.pearlorganisation

import android.content.Context
import android.content.SharedPreferences




 class PrefManager(var context: Context) {
     // Shared preferences file name
     private val PREF_NAME = "welcome"
     private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
     // shared pref mode
     var PRIVATE_MODE = 0
    var pref: SharedPreferences? = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref!!.edit()
    var _context: Context? = context





 /*   fun PrefManager(context: Context?) {
        pref = _context
        editor =
    }*/

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor!!.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor!!.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref!!.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

     fun setMpin(mpin: String){
         editor!!.putString("mpin",mpin)
         editor?.commit()
     }


     fun getMpin():String{
         return pref?.getString("mpin","").toString()
     }

     fun setToken(token:String){
         editor?.putString("token",token)
         editor?.commit()
     }

     fun getToken():String{
         return pref?.getString("token","null").toString()
     }

     fun setRideId(ride_id:String){
         editor?.putString("ride_id",ride_id)
         editor?.commit()
     }

     fun getRideId():String{
         return pref?.getString("ride_id","null").toString()
     }

     fun setVehicleId(vehicle_id:String){
         editor?.putString("vehicle_id",vehicle_id)
         editor?.commit()
     }

     fun getVehicleId():String{
         return pref?.getString("vehicle_id","null").toString()
     }

     fun setUserId(userid:String){
         editor?.putString("userid",userid)
         editor?.commit()
     }
     fun getUserId():String{
         return pref?.getString("userid","").toString()
     }

     fun setvehicle_type_id(vehicle_type_id:String){
         editor?.putString("vehicle_type_id",vehicle_type_id)
         editor?.commit()
     }

     fun getvehicle_type_id():String{
         return pref?.getString("vehicle_type_id"," ").toString()
     }

     fun setride_id(ride_id:String){
         editor?.putString("ride_id",ride_id)
         editor?.commit()
     }

     fun getride_id():String{
         return pref?.getString("ride_id"," ").toString()
     }




}