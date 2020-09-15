package com.example.aircontrol

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.aircontrol.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var binding : FragmentMapBinding
    val listOfPollutionData: ArrayList<PollutionData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val request = AQICNService.buildService(AirQualityAPI::class.java)

        for(city in Cities.citiesArray) {
            val call = request.getCityPollutionData(city)
            call.enqueue(object : Callback<PollutionData> {
                override fun onResponse(call: Call<PollutionData>, response: Response<PollutionData>) {
                    Log.d("test", response.body().toString())
                    val data = response.body()
                    if (data != null) {
                        val city = LatLng(data.data.city.geo[0], data.data.city.geo[1])
                        mMap.addMarker(MarkerOptions().position(city).title(data.data.city.name).snippet(data.data.idx.toString()))
                        listOfPollutionData.add(data)
                    }
                }

                override fun onFailure(call: Call<PollutionData>, t: Throwable) {
                    Log.d("test", t.message)
                }
            })
        }

        mMap.let {
            it.setOnMarkerClickListener {

                val cityData = listOfPollutionData.filter { x -> x.data.idx.toString() == it.snippet }
                val bundle = bundleOf("cityName" to it.title, "cityData" to cityData[0])
                view?.findNavController()?.navigate(R.id.action_mapFragment_to_markerDetailsFragment, bundle)

                true
            }
        }

    }
}