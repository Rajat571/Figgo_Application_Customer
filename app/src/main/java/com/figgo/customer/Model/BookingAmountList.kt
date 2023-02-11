package com.figgo.customer.Model

import android.os.Parcel
import android.os.Parcelable

data class BookingAmountList (var compactcab:String, var swiftcab: String, var carimg:String, var carimg1:String, var amountyellow:String, var amountpurple:String, var driverchange:String, var driverchangepurple:String) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(compactcab)
        parcel.writeString(swiftcab)
        parcel.writeString(carimg)
        parcel.writeString(carimg1)
        parcel.writeString(amountyellow)
        parcel.writeString(amountpurple)
        parcel.writeString(driverchange)
        parcel.writeString(driverchangepurple)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookingAmountList> {
        override fun createFromParcel(parcel: Parcel): BookingAmountList {
            return BookingAmountList(parcel)
        }

        override fun newArray(size: Int): Array<BookingAmountList?> {
            return arrayOfNulls(size)
        }
    }

}