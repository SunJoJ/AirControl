package com.example.aircontrol.adapters

import com.example.aircontrol.models.PollutionData
import java.text.FieldPosition

interface AdapterSelectedSensorsListClickListener {
    fun onSelectButtonClickListener(cityName: String)
    fun onClickListener(cityData: PollutionData)
}