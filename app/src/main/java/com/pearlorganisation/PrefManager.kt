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
    var _context: Context? = null





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

     fun setMpinonetime(mpin: String){
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

     fun setUserId(userid:String){
         editor?.putString("userid",userid)
         editor?.commit()
     }
     fun getUserId():String{
         return pref?.getString("userid","").toString()
     }


}