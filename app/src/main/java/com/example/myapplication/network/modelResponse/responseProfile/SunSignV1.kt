package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SunSignV1(
    val id: Int,
    val name: String
) : Parcelable