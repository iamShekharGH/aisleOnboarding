package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.datastore.DatastoreManager
import com.example.myapplication.network.AisleApi
import com.example.myapplication.network.modelResponse.responseProfile.ResponseProfile
import com.example.myapplication.network.modelsRequest.RequestMobileNo
import com.example.myapplication.network.modelsRequest.RequestMobileNoOTP
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "AisleRepository"

@Singleton
class AisleRepository @Inject constructor(
    private val api: AisleApi,
    private val datastoreManager: DatastoreManager
) {

    private val repositoryChannel = Channel<AisleRepositoryEvents>()
    val repositoryChannelAsFlow = repositoryChannel.receiveAsFlow()


    suspend fun sendMobileNumber(text: String) {
        val responseMobileNo = api.phoneNumberLogin(RequestMobileNo("+91$text"))

//        if (responseMobileNo.code() == 200) {
//            repositoryChannel.send(AisleRepositoryEvents.ResponseSuccess("" + responseMobileNo.code()))
//        }
        Log.i(TAG, "sendMobileNumber: status -> ${responseMobileNo.body()?.status}")
        if (responseMobileNo.body()?.status == true) {
            // In the string below, I would send the response message or pre-written messages based on response code.
            repositoryChannel.send(AisleRepositoryEvents.ResponseSuccess("" + responseMobileNo.code()))
        } else {
            repositoryChannel.send(AisleRepositoryEvents.ResponseFailed(""))
        }
    }

    suspend fun sendOtpTo(mobNo: String, otp: String) {
        val responseOtp = api.verifyOtp(RequestMobileNoOTP(number = "+91$mobNo", otp = otp))
        Log.i(TAG, "sendOtpTo: $responseOtp  token -> ${responseOtp.body()?.token}")

        if (responseOtp.isSuccessful) {
            val t = responseOtp.body()!!.token
            datastoreManager.updateTokenInfo(token = t)
            repositoryChannel.send(AisleRepositoryEvents.LoginSuccessful)
            delay(1000)
            getProfile()
        } else {
            repositoryChannel.send(AisleRepositoryEvents.ResponseFailed(""))
        }
    }

    private suspend fun getProfile() {
        val t = datastoreManager.prefFlow.first().token
        Log.i(TAG, "getProfile: token -> $t")
        if (t.isEmpty()) {
            //show Message
            repositoryChannel.send(AisleRepositoryEvents.ResponseFailed("Please Login again."))
            return
        }
        try {
            val responseProfile = api.testProfileList(t)
            if (responseProfile.isSuccessful) {
                repositoryChannel.send(AisleRepositoryEvents.LoadProfileFragment(responseProfile.body()!!))
            } else {
                repositoryChannel.send(AisleRepositoryEvents.ResponseFailed(""))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

sealed class AisleRepositoryEvents {
    data class ResponseFailed(val text: String) : AisleRepositoryEvents()
    data class ResponseSuccess(val text: String) : AisleRepositoryEvents()
    data class LoadProfileFragment(val profile: ResponseProfile) : AisleRepositoryEvents()
    object LoginSuccessful : AisleRepositoryEvents()
}