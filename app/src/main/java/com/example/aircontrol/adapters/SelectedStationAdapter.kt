package com.example.aircontrol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.aircontrol.R
import com.example.aircontrol.fragments.SelectedStationsList
import com.example.aircontrol.utils.QualityRanges
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView

class SelectedStationAdapter(private val context: Context,
                             private val dataSource: ArrayList<SelectedStationsList.PollutionDataWithRequestName>,
                             private val adapterSelectedSensorsListClickListener: AdapterSelectedSensorsListClickListener) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val rowView = inflater.inflate(R.layout.fragment_marker_details, parent, false)

        val data = getItem(position) as SelectedStationsList.PollutionDataWithRequestName
        val cityData = data.pollutionData
        val cityName = data.cityName

        val detailsTextView = rowView.findViewById(R.id.detailsTextView) as MaterialTextView
        val addressTextView = rowView.findViewById(R.id.addressTextView) as MaterialTextView
        val iconChoose = rowView.findViewById(R.id.iconChoose) as MaterialCheckBox
        val markerDetailsFragmentLayout = rowView.findViewById(R.id.markerDetailsFragmentLayout) as ConstraintLayout

        iconChoose.isChecked = true
        detailsTextView.text = "AQI " + cityData?.data?.aqi.toString()
        addressTextView.text = cityData.data.city.name

        detailsTextView.background = cityData.data.aqi.let {
            it.let { it1 -> QualityRanges.getIndexColor(it1) }
        }.let { getDrawable(context, it) }

        markerDetailsFragmentLayout.background = getDrawable(context, R.drawable.rounded_corner)

        iconChoose.setOnClickListener {
            adapterSelectedSensorsListClickListener.onSelectButtonClickListener(cityName)
        }

        markerDetailsFragmentLayout.setOnClickListener{
            adapterSelectedSensorsListClickListener.onClickListener(cityData)
        }

        return rowView
    }


}