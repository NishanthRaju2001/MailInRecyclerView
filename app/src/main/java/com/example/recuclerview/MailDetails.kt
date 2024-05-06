package com.example.recuclerview

import android.os.Parcel
import android.os.Parcelable


data class MailDetails(val sender: String?, val title: String?, val body: String?, val time:Long=0L):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sender)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeLong(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MailDetails> {
        override fun createFromParcel(parcel: Parcel): MailDetails {
            return MailDetails(parcel)
        }

        override fun newArray(size: Int): Array<MailDetails?> {
            return arrayOfNulls(size)
        }
    }
}
