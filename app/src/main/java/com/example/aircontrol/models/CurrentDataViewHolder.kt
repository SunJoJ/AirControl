package com.example.aircontrol.models

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aircontrol.R
import com.example.aircontrol.adapters.CurrentDataClickListener
import com.example.aircontrol.utils.QualityRanges.getParameterColor
import com.google.android.material.circularreveal.CircularRevealRelativeLayout

class CurrentDataViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val currentDataClickListener: CurrentDataClickListener) : RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_data_parameter_item, parent, false)) {
    private var colorIndicator: CircularRevealRelativeLayout? = null
    private var titlePollutant: AppCompatTextView? = null
    private var dataPollutant: AppCompatTextView? = null
    private var dataItem: RelativeLayout? = null

    init {
        colorIndicator = itemView.findViewById(R.id.colorIndicator)
        titlePollutant = itemView.findViewById(R.id.titlePollutant)
        dataPollutant = itemView.findViewById(R.id.dataPollutant)
        dataItem = itemView.findViewById(R.id.dataItem)
    }

    fun bind(currentData: CurrentData) {
        titlePollutant?.text = currentData.pollutantTitle
        dataPollutant?.text = currentData.actualValue.toString()
        colorIndicator?.background = itemView.context.getDrawable(getParameterColor(currentData))

        dataItem?.setOnClickListener{
            currentDataClickListener.onItemClickListener(currentData.pollutantTitle)
        }
    }

}