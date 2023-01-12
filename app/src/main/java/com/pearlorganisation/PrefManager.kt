package com.pearlorganisation

import android.content.Context
import android.content.SharedPreferences




 class PrefManager(var context: Context) {
     // Shared preferences file name
     private val PREF_NAME = "welcome"
     private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
     private val IS_VALID_LOGIN = "IsValidLogin"
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

     fun setOtp(otp:String){
         editor?.putString("otp",otp)
         editor?.commit()
     }

     fun getOtp():String{
         return pref?.getString("otp","null").toString()
     }
     fun setAccountDetails(email:String,name:String,photoURL:String){
         editor?.putString("EmailID",email)
         editor?.putString("EmailName",name)
         editor?.putString("PhotoUrl",photoURL)
         editor?.commit()
     }
     fun getAccountMail():String{
         return pref?.getString("EmailID","")!!
     }
     fun getAccountName():String{
         return pref?.getString("EmailName","")!!
     }
     fun getAccountPhotoURL():String{
         return pref?.getString("PhotoUrl","")!!
     }
     fun setBookingNo(book_no:String){
         editor?.putString("book_no",book_no)
         editor?.commit()
     }

     fun getBookingNo():String{
         return pref?.getString("book_no","null").toString()
     }

     fun setVehicleId(vehicle_id:String){
         editor?.putString("vehicle_id",vehicle_id)
         editor?.commit()
     }

     fun getVehicleId():String{
         return pref?.getString("vehicle_id","null").toString()
     }



     fun getdriver_id(): String {
         return pref?.getString("driver_id","driver_id").toString()

     }

     fun setUserId(userid:String){
         editor?.putString("userid",userid)
         editor?.commit()
     }
     fun getUserId():String{
         return pref?.getString("userid","").toString()
     }

     fun setisValidLogin(isFirstTime: Boolean) {
         editor!!.putBoolean(IS_VALID_LOGIN, isFirstTime)
         editor!!.commit()
     }

     fun isValidLogin(): Boolean {
         return pref!!.getBoolean(IS_VALID_LOGIN, false)
     }






 }