package com.example.aircontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.aircontrol.databinding.FragmentCityDataBinding
import kotlinx.android.synthetic.main.fragment_city_data.*
import kotlinx.android.synthetic.main.fragment_marker_details.*

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

        testTextView.text = cityData?.data?.city?.name
    }


}