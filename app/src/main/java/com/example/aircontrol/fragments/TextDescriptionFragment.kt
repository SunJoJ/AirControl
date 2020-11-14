package com.example.aircontrol.fragments

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.aircontrol.R
import com.example.aircontrol.databinding.FragmentTextDescriptionBinding
import kotlinx.android.synthetic.main.fragment_text_description.*


class TextDescriptionFragment : Fragment() {

    lateinit var binding : FragmentTextDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_text_description, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var sp: Spanned? = null
        when(arguments?.getString("title")) {
            "PM10" -> {
                sp = Html.fromHtml(getString(R.string.PM10))
            }
            "PM2.5" -> {
                sp = Html.fromHtml(getString(R.string.PM25))
            }
            "PM25" -> {
                sp = Html.fromHtml(getString(R.string.PM25))
            }
            "O3" -> {
                sp = Html.fromHtml(getString(R.string.O3))
            }
            "NO2" -> {
                sp = Html.fromHtml(getString(R.string.NO2))
            }
            "C6H6" -> {
                sp = Html.fromHtml(getString(R.string.C6H6))
            }
            "CO" -> {
                sp = Html.fromHtml(getString(R.string.CO))
            }
            "SO2" -> {
                sp = Html.fromHtml(getString(R.string.SO2))
            }
        }
            description_text_view.text = sp
    }

}

