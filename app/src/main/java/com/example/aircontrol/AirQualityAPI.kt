package com.example.aircontrol

import retrofit2.Call
import retrofit2.http.GET


interface AirQualityAPI {

    @GET("beijing/?token=5473021214ba43de0e18ddedaf57f40ef844de84")
    fun getCityPollutionData(): Call<PollutionData>

}