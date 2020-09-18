package com.example.aircontrol.utils

import android.content.Context
import com.example.aircontrol.models.MarkerItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer


class CustomClusterRenderer(context: Context, map: GoogleMap?, clusterManager: ClusterManager<MarkerItem>?)
    : DefaultClusterRenderer<MarkerItem>(context, map, clusterManager) {

    private val mContext: Context = context
    override fun onBeforeClusterItemRendered(item: MarkerItem, markerOptions: MarkerOptions) {
        val bitmapDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromResource(
            QualityRanges.getIconMarker(
                item.snippet.toInt()
            )
        )
        markerOptions.icon(bitmapDescriptor)
    }

}