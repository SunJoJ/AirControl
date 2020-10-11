package com.example.aircontrol.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.aircontrol.R
import com.example.aircontrol.adapters.AdapterRatingListClickListener
import com.example.aircontrol.adapters.RatingListAdapter
import com.example.aircontrol.client.AQICNService
import com.example.aircontrol.client.AirQualityAPI
import com.example.aircontrol.databinding.FragmentListBinding
import com.example.aircontrol.models.Cities
import com.example.aircontrol.models.PollutionData
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(), AdapterRatingListClickListener {

    lateinit var binding : FragmentListBinding
    data class DataWithCode(val data: PollutionData, val code: String)
    val listOfPollutionData: ArrayList<DataWithCode> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //return inflater.inflate(R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(listOfPollutionData.isEmpty()) {
            val request = AQICNService.buildService(AirQualityAPI::class.java)
            for (city in Cities.allCitiesArray) {
                val call = request.getCityPollutionData(city.cityName)
                call.enqueue(object : Callback<PollutionData> {
                    override fun onResponse(
                        call: Call<PollutionData>,
                        response: Response<PollutionData>
                    ) {

                        val data = response.body()
                        if (data != null) {
                            listOfPollutionData.add(DataWithCode(data, city.isoCode))
                            listOfPollutionData.sortByDescending { it.data.data.aqi }
                            val adapter = context?.let { RatingListAdapter(it, listOfPollutionData, this@ListFragment) }
                            ratingList.adapter = adapter
                            adapter?.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<PollutionData>, t: Throwable) {
                        Log.d("test", t.message)
                    }
                })
            }
        }
        val adapter = context?.let { RatingListAdapter(it, listOfPollutionData, this@ListFragment) }
        ratingList.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun onItemClickListener(cityData: DataWithCode) {
        val bundle = bundleOf("cityName" to cityData.data.data.city.name, "cityData" to cityData.data)
            view?.findNavController()?.navigate(R.id.action_ratingAndNewsFragment_to_cityDataFragment, bundle)
    }
}