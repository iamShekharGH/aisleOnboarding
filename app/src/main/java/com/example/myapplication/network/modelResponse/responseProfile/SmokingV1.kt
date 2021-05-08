package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmokingV1(
    val id: Int,
    val name: String,
    val name_alias: String
) : Parcelable