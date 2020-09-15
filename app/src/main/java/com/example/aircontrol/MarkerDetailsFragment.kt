package com.example.aircontrol

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.aircontrol.databinding.FragmentMarkerDetailsBinding
import kotlinx.android.synthetic.main.fragment_marker_details.*
import java.io.Serializable


/**
 * A simple [Fragment] subclass.
 * Use the [MarkerDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarkerDetailsFragment : DialogFragment() {

    lateinit var binding : FragmentMarkerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_marker_details, container, false)


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val window: Window? = dialog?.window
        window?.setGravity(Gravity.CENTER)
        val params = window!!.attributes
        params.width = 1100
        params.height = 470
        params.y = 700
        window.attributes = params
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addressTextView.text = arguments?.getString("cityName")
        val cityData: PollutionData? = arguments?.getParcelable("cityData")
        detailsTextView.text = "AQI " + cityData?.data?.aqi.toString()

        detailsTextView.background = cityData?.data?.aqi.let {
            it?.let { it1 -> QualityRanges.getIndexColor(it1) }
        }?.let { resources.getDrawable(it) }


        markerDetailsFragmentLayout.setOnClickListener {
            val bundle = bundleOf("cityName" to  arguments?.getString("cityName"), "cityData" to  arguments?.getParcelable("cityData"))
            findNavController().navigate(R.id.action_markerDetailsFragment_to_cityDataFragment, bundle)
        }

    }
}