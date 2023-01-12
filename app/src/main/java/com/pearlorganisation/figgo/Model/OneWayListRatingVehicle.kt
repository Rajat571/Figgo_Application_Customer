package com.pearlorganisation.figgo.Model

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray

data class OneWayListRatingVehicle(
    var driver_id:String,
    var name:String,
    var year:String,
    var price:String,
    var rating:String,
    var ride_id: String
)