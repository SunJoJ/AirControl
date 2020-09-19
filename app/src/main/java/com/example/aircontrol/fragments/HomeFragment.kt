package com.example.aircontrol.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.aircontrol.R
import com.example.aircontrol.client.AQICNService
import com.example.aircontrol.client.AirQualityAPI
import com.example.aircontrol.databinding.FragmentHomeBinding
import com.example.aircontrol.models.PollutionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val request = AQICNService.buildService(AirQualityAPI::class.java)
        val call = request.getNearestCityPollutionData()
        call.enqueue(object : Callback<PollutionData> {
            override fun onResponse(call: Call<PollutionData>, response: Response<PollutionData>) {
                val data = response.body()
                val bundle = bundleOf("cityName" to (data?.data?.city?.name), "cityData" to  data)
                findNavController().navigate(R.id.action_homeFragment_to_cityDataFragment, bundle)
            }

            override fun onFailure(call: Call<PollutionData>, t: Throwable) {

            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}