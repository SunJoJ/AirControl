package com.example.aircontrol.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aircontrol.fragments.ClosestCityFragment
import com.example.aircontrol.fragments.SelectedStationsList

class HomePagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> ClosestCityFragment()
        1 -> SelectedStationsList()
        else -> throw IllegalStateException("Unexpected position $position")
    }
}