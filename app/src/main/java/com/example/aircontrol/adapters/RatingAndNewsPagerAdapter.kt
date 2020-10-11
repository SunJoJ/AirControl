package com.example.aircontrol.adapters

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aircontrol.fragments.ListFragment
import com.example.aircontrol.fragments.NewsFragment

class RatingAndNewsPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> ListFragment()
        1 -> NewsFragment()
        else -> throw IllegalStateException("Unexpected position $position")
    }
}