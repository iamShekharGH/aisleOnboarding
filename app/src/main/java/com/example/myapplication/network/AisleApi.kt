package com.example.myapplication.network

import com.example.myapplication.network.modelResponse.ResponseMobileNum
import com.example.myapplication.network.modelResponse.ResponseMobileNumOtp
import com.example.myapplication.network.modelResponse.ResponseTestProfileList
import com.example.myapplication.network.modelResponse.responseProfile.ResponseProfile
import com.example.myapplication.network.modelsRequest.RequestMobileNo
import com.example.myapplication.network.modelsRequest.RequestMobileNoOTP
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AisleApi {

    // For simplicity using Response<..> and not Call<..>

    companion object {
        const val BASE_URL = "https://testa2.aisle.co/V1/users/"
        const val CLINT_ID = "__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720"
    }


    @Headers("Content-Type: application/json", "Cookie: $CLINT_ID")
    @POST("phone_number_login/")
    suspend fun phoneNumberLogin(@Body req: RequestMobileNo): Response<ResponseMobileNum>

    @Headers("Content-Type: application/json", "Cookie: $CLINT_ID")
    @POST("verify_otp/")
    suspend fun verifyOtp(@Body req: RequestMobileNoOTP): Response<ResponseMobileNumOtp>

    @Headers("Content-Type: application/json", "Cookie: $CLINT_ID")
    @GET("test_profile_list/")
    suspend fun testProfileList(@Header("Authorization:") authToken: String): Response<ResponseProfile>
}

/*
* 1. Send Phone Number API

curl --location --request POST 'https://testa2.aisle.co/V1/users/phone_number_login' \
--header 'Content-Type: application/json' \
--header 'Cookie: __cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720' \
--data-raw '{
    "number": "+919876543212"
}'

2. Verify OTP API

curl --location --request POST 'https://testa2.aisle.co/V1/users/verify_otp' \
--header 'Content-Type: application/json' \
--header 'Cookie: __cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720' \
--data-raw '{
    "number": "+919876543212",
    "otp": "1234"
}'

3. Fetch Profile list API

curl --location --request GET 'https://testa2.aisle.co/V1/users/test_profile_list' \
--header 'Authorization: AUTHORIZATION_TOKEN' \
--header 'Cookie: __cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720'
* */