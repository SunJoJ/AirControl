package com.example.aircontrol.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aircontrol.models.PollutionData
import com.example.aircontrol.R
import com.example.aircontrol.adapters.CurrentDataAdapter
import com.example.aircontrol.databinding.FragmentCityDataBinding
import com.example.aircontrol.models.CurrentData
import com.example.aircontrol.utils.QualityRanges
import kotlinx.android.synthetic.main.fragment_city_data.*
import kotlinx.android.synthetic.main.fragment_city_data.addressTextView
import kotlinx.android.synthetic.main.fragment_city_data.detailsTextView

/**
 * A simple [Fragment] subclass.
 * Use the [CityDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityDataFragment : Fragment() {

    lateinit var binding : FragmentCityDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_data, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityData: PollutionData? = arguments?.getParcelable("cityData")

        addressTextView.text = arguments?.getString("cityName")
        detailsTextView.text = "AQI " + cityData?.data?.aqi.toString()

        detailsTextView.background = cityData?.data?.aqi.let {
            it?.let { it1 -> QualityRanges.getIndexColor(it1) }
        }?.let { resources.getDrawable(it) }

        cityName.text = cityData?.data?.attributions?.get(0)?.name
        timeStamp.text = cityData?.data?.time?.s

        val currentData = listOf(
            CurrentData("co", cityData?.data?.iaqi?.co?.v),
            CurrentData("h", cityData?.data?.iaqi?.h?.v),
            CurrentData("no2", cityData?.data?.iaqi?.no2?.v),
            CurrentData("p", cityData?.data?.iaqi?.p?.v),
            CurrentData("pm10", cityData?.data?.iaqi?.pm10?.avg?.toDouble()),
            CurrentData("pm25", cityData?.data?.iaqi?.pm25?.avg?.toDouble()),
        )

        currentDataRecyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = CurrentDataAdapter(currentData)
        }

    }


}