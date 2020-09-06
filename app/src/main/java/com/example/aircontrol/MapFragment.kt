package com.example.aircontrol

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        
        val service = AQICNService.buildService()
        val call = service.getCityPollutionData()

        call.enqueue(object : Callback<PollutionData> {
            override fun onResponse(call: Call<PollutionData>, response: Response<PollutionData>) {
                Log.d("test", response.body().toString())
                val data = response.body()
                if (data != null) {
                    Log.d("test", data.status)
                }
            }

            override fun onFailure(call: Call<PollutionData>, t: Throwable) {

            }
        })


        return inflater.inflate(R.layout.fragment_map, container, false)
    }
}