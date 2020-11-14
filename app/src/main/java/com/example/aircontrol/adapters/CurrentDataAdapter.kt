package com.example.aircontrol.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aircontrol.models.CurrentData
import com.example.aircontrol.models.CurrentDataViewHolder

class CurrentDataAdapter(private val data: List<CurrentData>, private val currentDataClickListener: CurrentDataClickListener) : RecyclerView.Adapter<CurrentDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return CurrentDataViewHolder(inflater, parent, currentDataClickListener)
    }

    override fun onBindViewHolder(holder: CurrentDataViewHolder, position: Int) {
        val currentData = data[position]
        holder.bind(currentData)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}