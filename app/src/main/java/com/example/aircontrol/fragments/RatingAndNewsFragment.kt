package com.example.aircontrol.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.aircontrol.R
import com.example.aircontrol.adapters.RatingAndNewsPagerAdapter
import com.example.aircontrol.databinding.FragmentRatingAndNewsBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_rating_and_news.*



/**
 * A simple [Fragment] subclass.
 * Use the [RatingAndNewsFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class RatingAndNewsFragment : Fragment() {

    lateinit var binding : FragmentRatingAndNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rating_and_news, container, false)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ratingAndNewsPagerAdapter = RatingAndNewsPagerAdapter(this)
        pager.adapter = ratingAndNewsPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when(position) {
                0 -> "Rating"
                1 -> "News"
                else -> "Unknown"
            }
        }.attach()

    }


}