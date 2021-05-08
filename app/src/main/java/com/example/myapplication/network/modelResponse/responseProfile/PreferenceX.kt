package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreferenceX(
    val answer: String,
    val answer_id: Int,
    val first_choice: String,
    val second_choice: String
) : Parcelable