package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseProfile(
    val invites: Invites,
    val likes: Likes
) : Parcelable