package com.example.aircontrol

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AQICNService {

    private val client = OkHttpClient.Builder().build()
    private val token = "5473021214ba43de0e18ddedaf57f40ef844de84"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.waqi.info/feed/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()



    fun buildService(): AirQualityAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.waqi.info/feed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AirQualityAPI::class.java)
    }

}