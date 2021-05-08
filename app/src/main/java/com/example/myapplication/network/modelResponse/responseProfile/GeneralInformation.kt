package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralInformation(
    val age: Int,
    val cast: String,
    val date_of_birth: String,
    val date_of_birth_v1: String,
    val diet: String,
    val drinking: String,
    val drinking_v1: DrinkingV1,
    val faith: Faith,
    val first_name: String,
    val gender: String,
    val height: Int,
    val kid: String,
    val location: Location,
    val marital_status: String,
    val marital_status_v1: MaritalStatusV1,
    val mother_tongue: MotherTongue,
    val pet: String,
    val politics: String,
    val ref_id: String,
    val settle: String,
    val smoking: String,
    val smoking_v1: SmokingV1,
    val sun_sign: String,
    val sun_sign_v1: SunSignV1
) : Parcelable