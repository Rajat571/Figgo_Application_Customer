package com.pearlorganisation.figgo.Model

import android.os.Parcel
import android.os.Parcelable

data class OneWayListRatingVehicle (var vehiclemodel:String, var raingcountlist: String, var ride_service_rating:String,var reject:String, var acceptcountlist:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(vehiclemodel)
        parcel.writeString(raingcountlist)
        parcel.writeString(ride_service_rating)
        parcel.writeString(reject)
        parcel.writeString(acceptcountlist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OneWayListRatingVehicle> {
        override fun createFromParcel(parcel: Parcel): OneWayListRatingVehicle {
            return OneWayListRatingVehicle(parcel)
        }

        override fun newArray(size: Int): Array<OneWayListRatingVehicle?> {
            return arrayOfNulls(size)
        }
    }
}