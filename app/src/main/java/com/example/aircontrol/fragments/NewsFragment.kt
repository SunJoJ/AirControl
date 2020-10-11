package com.example.aircontrol.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.aircontrol.R
import com.example.aircontrol.adapters.AdapterNewsListClickListener
import com.example.aircontrol.adapters.NewsListAdapter
import com.example.aircontrol.databinding.FragmentNewsBinding
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment(), AdapterNewsListClickListener {

    lateinit var binding : FragmentNewsBinding
    data class NewsData(val title: String, val date: String, val image: Int)
    private val newsDataArray: ArrayList<NewsData> = arrayListOf(NewsData("PM10", "10.02.2020", R.drawable.pollution1), NewsData("SO2", "10.02.2020", R.drawable.pollution1),
                                                                NewsData("PM2.5", "10.02.2020", R.drawable.pollution2), NewsData("C6H6", "10.02.2020", R.drawable.pollution2),
                                                                NewsData("O3", "10.02.2020", R.drawable.pollution3), NewsData("CO", "10.02.2020", R.drawable.pollution3),
                                                                NewsData("NO2", "10.02.2020", R.drawable.pollution4))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = context?.let { NewsListAdapter(it, newsDataArray, this@NewsFragment) }
        newsList.adapter = adapter
        adapter?.notifyDataSetChanged()

    }

    override fun onItemClickListener(pos: Int) {
        val bundle = bundleOf("title" to newsDataArray[pos].title)
        view?.findNavController()?.navigate(R.id.action_ratingAndNewsFragment_to_textDescriptionFragment, bundle)
    }



}