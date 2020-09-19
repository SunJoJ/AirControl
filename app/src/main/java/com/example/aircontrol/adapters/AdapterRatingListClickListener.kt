package com.example.aircontrol.adapters

import com.example.aircontrol.fragments.ListFragment

interface AdapterRatingListClickListener {
    fun onItemClickListener(cityData: ListFragment.DataWithCode)
}