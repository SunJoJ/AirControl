package com.example.aircontrol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.example.aircontrol.R
import com.example.aircontrol.fragments.ListFragment
import com.example.aircontrol.fragments.NewsFragment
import com.example.aircontrol.utils.QualityRanges
import com.google.android.material.textview.MaterialTextView
import com.jwang123.flagkit.FlagKit

class NewsListAdapter(private val context: Context,
                      private val dataSource: ArrayList<NewsFragment.NewsData>,
                      private val adapterNewsListClickListener: AdapterNewsListClickListener) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.fragment_news_item, parent, false)

        val newsData = getItem(pos) as NewsFragment.NewsData

        val newsTitle = rowView.findViewById(R.id.newsTitleTextView) as MaterialTextView
        val newsDate = rowView.findViewById(R.id.newsDateTextView) as MaterialTextView
        val newsImage = rowView.findViewById(R.id.newsImageView) as ImageView

        newsTitle.text = newsData.title
        newsDate.text = newsData.date
        newsImage.background = AppCompatResources.getDrawable(context, newsData.image)

        rowView.setOnClickListener {
            adapterNewsListClickListener.onItemClickListener(pos)
        }

        return rowView
    }

}