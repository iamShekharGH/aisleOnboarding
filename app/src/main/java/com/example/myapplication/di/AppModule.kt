package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.datastore.DatastoreManager
import com.example.myapplication.network.AisleApi
import com.example.myapplication.repository.AisleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()


    @Provides
    @Singleton
    fun provideRetrofitObject(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .baseUrl(AisleApi.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAisleApi(retrofit: Retrofit): AisleApi =
        retrofit.create(AisleApi::class.java)


    @Provides
    @Singleton
    fun provideRepository(api: AisleApi, datastoreManager: DatastoreManager): AisleRepository =
        AisleRepository(api, datastoreManager)


}
