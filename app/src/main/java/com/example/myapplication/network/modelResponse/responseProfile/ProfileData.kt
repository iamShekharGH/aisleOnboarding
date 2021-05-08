package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileData(
    val invitation_type: String,
    val preferences: List<PreferenceX>,
    val question: String
) : Parcelable