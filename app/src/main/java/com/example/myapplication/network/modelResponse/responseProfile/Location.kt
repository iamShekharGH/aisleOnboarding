package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val full: String,
    val summary: String
) : Parcelable