package com.example.myapplication.network.modelResponse.responseProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    val approved_time: Double,
    val general_information: GeneralInformation,
    val has_active_subscription: Boolean,
    val icebreakers: String,
    val instagram_images: String,
    val is_facebook_data_fetched: Boolean,
    val last_seen: String,
    val last_seen_window: String,
    val lat: String,
    val latest_poll: String,
    val lng: String,
    val meetup: String,
    val online_code: Int,
    val photos: List<Photo>,
    val poll_info: String,
    val preferences: List<Preference>,
    val profile_data_list: List<ProfileData>,
    val show_concierge_badge: Boolean,
    val story: String,
    val user_interests: List<String>,
    val verification_status: String,
    val work: Work
) : Parcelable