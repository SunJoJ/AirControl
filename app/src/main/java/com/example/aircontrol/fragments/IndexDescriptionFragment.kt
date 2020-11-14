package com.example.aircontrol.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.aircontrol.R
import com.example.aircontrol.databinding.FragmentIndexDescriptionBinding


class IndexDescriptionFragment : Fragment() {

    lateinit var binding : FragmentIndexDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_index_description, container, false)



        return binding.root
    }


}