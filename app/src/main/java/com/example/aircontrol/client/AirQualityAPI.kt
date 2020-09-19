package com.example.aircontrol.client

import com.example.aircontrol.models.PollutionData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface AirQualityAPI {

    @GET
    fun getCityPollutionDataUrl(@Url url: String): Call<PollutionData>

    @GET("{city}/?token=5473021214ba43de0e18ddedaf57f40ef844de84")
    fun getCityPollutionData(@Path(value = "city") city: String): Call<PollutionData>

    @GET("here/?token=5473021214ba43de0e18ddedaf57f40ef844de84")
    fun getNearestCityPollutionData() : Call<PollutionData>

}