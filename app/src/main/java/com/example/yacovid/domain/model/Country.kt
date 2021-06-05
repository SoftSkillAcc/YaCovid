package com.example.yacovid.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country") val country: String?,
    @SerializedName("code") val code: String?,
    @SerializedName("confirmed") val confirmed: Int,
    @SerializedName("recovered") val recovered: Int,
    @SerializedName("deaths") val deaths: Int,
    @SerializedName("lastUpdate") val lastUpdate: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeString(code)
        parcel.writeInt(confirmed)
        parcel.writeInt(recovered)
        parcel.writeInt(deaths)
        parcel.writeString(lastUpdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}