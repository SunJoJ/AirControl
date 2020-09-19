package com.example.aircontrol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aircontrol.R
import com.example.aircontrol.databinding.FragmentListBinding
import com.example.aircontrol.fragments.ListFragment
import com.example.aircontrol.models.PollutionData
import com.example.aircontrol.utils.QualityRanges
import com.example.aircontrol.utils.QualityRanges.getIndexColor
import com.jwang123.flagkit.FlagKit

class RatingListAdapter(private val context: Context,
                        private val dataSource: ArrayList<ListFragment.DataWithCode>,
                        private val adapterRatingListClickListener: AdapterRatingListClickListener) : BaseAdapter() {

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
        val rowView = inflater.inflate(R.layout.fragment_rating_item, parent, false)

        val cityData = getItem(pos) as ListFragment.DataWithCode

        val aqiIndex = rowView.findViewById(R.id.aqiIndex) as TextView
        val nameOfCity = rowView.findViewById(R.id.nameOfCity) as TextView
        val position = rowView.findViewById(R.id.position) as TextView
        val indexColor = rowView.findViewById(R.id.indexColor) as RelativeLayout
        val flag = rowView.findViewById(R.id.flag) as ImageView

        aqiIndex.text = cityData.data.data.aqi.toString()
        indexColor.background = getDrawable(context, getIndexColor(cityData.data.data.aqi))
        flag.background = FlagKit.drawableWithFlag(context, cityData.code)

        nameOfCity.text = cityData.data.data.city.name
        position.text = (pos + 1).toString()

        rowView.setOnClickListener {
            adapterRatingListClickListener.onItemClickListener(cityData)
        }

        return rowView
    }

}