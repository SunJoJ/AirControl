package com.example.aircontrol.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aircontrol.R
import com.example.aircontrol.adapters.CurrentDataAdapter
import com.example.aircontrol.client.AQICNService
import com.example.aircontrol.client.AirQualityAPI
import com.example.aircontrol.databinding.FragmentCityDataBinding
import com.example.aircontrol.models.CurrentData
import com.example.aircontrol.models.PollutionData
import com.example.aircontrol.utils.QualityRanges
import kotlinx.android.synthetic.main.fragment_city_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClosestCityFragment : Fragment() {

    lateinit var binding : FragmentCityDataBinding
    var pollutionData: PollutionData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_data, container, false)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = AQICNService.buildService(AirQualityAPI::class.java)
        val call = request.getNearestCityPollutionData()
        call.enqueue(object : Callback<PollutionData> {
            override fun onResponse(call: Call<PollutionData>, response: Response<PollutionData>) {
                val cityData: PollutionData? = response.body()

                addressTextView.text = cityData?.data?.city?.name
                detailsTextView.text = getString(R.string.aqi, cityData?.data?.aqi.toString())

                detailsTextView.background = cityData?.data?.aqi.let {
                    it?.let { it1 -> QualityRanges.getIndexColor(it1) }
                }?.let { resources.getDrawable(it) }

                cityName.text = cityData?.data?.attributions?.get(0)?.name
                timeStamp.text = cityData?.data?.time?.s

                val currentData = listOf(
                    CurrentData("co", cityData?.data?.iaqi?.co?.v),
                    CurrentData("no2", cityData?.data?.iaqi?.no2?.v),
                    CurrentData("pm10", cityData?.data?.iaqi?.pm10?.avg?.toDouble()),
                    CurrentData("pm25", cityData?.data?.iaqi?.pm25?.avg?.toDouble()),
                )

                currentDataRecyclerView.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = CurrentDataAdapter(currentData)
                }

                tempText.text = getString(R.string.temperature, cityData?.data?.iaqi?.t?.v.toString())
                humidityText.text = cityData?.data?.iaqi?.h?.v.toString() + " %" //getString(R.string.humidity, cityData?.data?.iaqi?.h?.v.toString())
                windSpeedText.text = getString(R.string.wind_speed, cityData?.data?.iaqi?.w?.v.toString())

                pm10button.performClick()
                val chartData = cityData?.data?.forecast?.daily
                val data = chartData?.let { context?.let { it1 -> QualityRanges.paintChart(it, it1, "pm10", barChart) } }
                barChart.data = data
                barChart.axisRight.isEnabled = false
                barChart.description.isEnabled = false
                barChart.legend.isEnabled = false
                barChart.animateY(2000)


                toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
                    if (isChecked){
                        when (checkedId) {
                            pm10button.id -> {
                                val chartData = cityData?.data?.forecast?.daily
                                val data = chartData?.let {
                                    context?.let { it1 ->
                                        QualityRanges.paintChart(
                                            it,
                                            it1,
                                            "pm10",
                                            barChart
                                        )
                                    }
                                }
                                barChart.data = data
                                barChart.axisRight.isEnabled = false
                                barChart.description.isEnabled = false
                                barChart.legend.isEnabled = false
                                barChart.animateY(2000)
                            }
                            pm25button.id -> {
                                val chartData = cityData?.data?.forecast?.daily
                                val data = chartData?.let {
                                    context?.let { it1 ->
                                        QualityRanges.paintChart(
                                            it,
                                            it1,
                                            "pm25",
                                            barChart
                                        )
                                    }
                                }
                                barChart.data = data
                                barChart.axisRight.isEnabled = false
                                barChart.description.isEnabled = false
                                barChart.legend.isEnabled = false
                                barChart.animateY(2000)
                            }
                            o3button.id -> {
                                val chartData = cityData?.data?.forecast?.daily
                                val data = chartData?.let {
                                    context?.let { it1 ->
                                        QualityRanges.paintChart(
                                            it,
                                            it1,
                                            "o3",
                                            barChart
                                        )
                                    }
                                }
                                barChart.data = data
                                barChart.axisRight.isEnabled = false
                                barChart.description.isEnabled = false
                                barChart.legend.isEnabled = false
                                barChart.animateY(2000)
                            }
                        }
                    } else {
                        if(-1 == group.checkedButtonId) {
                            group.check(checkedId)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PollutionData>, t: Throwable) {

            }
        })

    }


}