package com.example.aircontrol.models

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aircontrol.R
import com.google.android.material.circularreveal.CircularRevealRelativeLayout

class CurrentDataViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_data_parameter_item, parent, false)) {
    private var colorIndicator: CircularRevealRelativeLayout? = null
    private var titlePollutant: AppCompatTextView? = null
    private var dataPollutant: AppCompatTextView? = null

    init {
        colorIndicator = itemView.findViewById(R.id.colorIndicator)
        titlePollutant = itemView.findViewById(R.id.titlePollutant)
        dataPollutant = itemView.findViewById(R.id.dataPollutant)
    }

    fun bind(currentData: CurrentData) {
        titlePollutant?.text = currentData.pollutantTitle
        dataPollutant?.text = currentData.actualValue.toString()
    }

}