package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val photo: String,
    val photo_id: Int,
    val selected: Boolean,
    val status: String
) : Parcelable