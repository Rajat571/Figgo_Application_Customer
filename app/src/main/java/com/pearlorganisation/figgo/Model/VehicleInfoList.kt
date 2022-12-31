package com.pearlorganisation.figgo.Model

import android.os.Parcel
import android.os.Parcelable

data class VehicleInfoList (var vehiclenumber:String, var activavehiclenumber:String, var drivername:String, var ravisingh:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(vehiclenumber)
        parcel.writeString(activavehiclenumber)
        parcel.writeString(drivername)
        parcel.writeString(ravisingh)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleInfoList> {
        override fun createFromParcel(parcel: Parcel): VehicleInfoList {
            return VehicleInfoList(parcel)
        }

        override fun newArray(size: Int): Array<VehicleInfoList?> {
            return arrayOfNulls(size)
        }
    }
}