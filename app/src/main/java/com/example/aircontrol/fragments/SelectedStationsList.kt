package com.example.aircontrol.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aircontrol.R
import com.example.aircontrol.adapters.*
import com.example.aircontrol.client.AQICNService
import com.example.aircontrol.client.AirQualityAPI
import com.example.aircontrol.databinding.FragmentSelectedStationsListBinding
import com.example.aircontrol.models.PollutionData
import kotlinx.android.synthetic.main.fragment_marker_details.*
import kotlinx.android.synthetic.main.fragment_selected_stations_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedStationsList : Fragment(), AdapterSelectedSensorsListClickListener {

    lateinit var binding : FragmentSelectedStationsListBinding

    data class PollutionDataWithRequestName(val cityName: String, val pollutionData: PollutionData)
    val listOfPollutionData: ArrayList<PollutionDataWithRequestName> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selected_stations_list, container, false)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("selectedStations", Context.MODE_PRIVATE)
        val selectedStations = sharedPref.getStringSet("selectedStations", mutableSetOf())

        val request = AQICNService.buildService(AirQualityAPI::class.java)
        for(city in selectedStations!!) {
            val call = request.getCityPollutionData(city)
            call.enqueue(object : Callback<PollutionData> {
                override fun onResponse(
                    call: Call<PollutionData>,
                    response: Response<PollutionData>
                ) {
                    val data = response.body()
                    data?.let { PollutionDataWithRequestName(city, it) }?.let {
                        listOfPollutionData.add(
                            it
                        )
                    }
                    val adapter =
                        context?.let { SelectedStationAdapter(it, listOfPollutionData, this@SelectedStationsList) }
                    selectedStationsList.adapter = adapter
                    adapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<PollutionData>, t: Throwable) {
                    Log.d("test", t.message)
                }
            })
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }

    override fun onSelectButtonClickListener(cityName: String) {
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("selectedStations", Context.MODE_PRIVATE)
        val selectedStations = sharedPref.getStringSet("selectedStations", mutableSetOf())
        selectedStations?.remove(cityName)
        Log.d("test", selectedStations.toString())
        with (sharedPref.edit()) {
            clear()
            putStringSet("selectedStations", selectedStations)
            apply()
            commit()
        }

        listOfPollutionData.removeAll{ it.cityName == cityName }
        val adapter = context?.let { SelectedStationAdapter(it, listOfPollutionData, this@SelectedStationsList) }
        selectedStationsList.adapter = adapter
        adapter?.notifyDataSetChanged()

    }

    override fun onClickListener(cityData: PollutionData) {
        val bundle = bundleOf("cityName" to  cityData.data.city.name, "cityData" to  cityData)
        findNavController().navigate(R.id.action_homeFragment_to_cityDataFragment, bundle)
    }

}