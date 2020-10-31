package com.example.aircontrol.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MarkerItem : ClusterItem {
    private val mPosition: LatLng
    private val mTitle: String
    private val mSnippet: String
    private val mCityName: String

    constructor(lat: Double, lng: Double) {
        mPosition = LatLng(lat, lng)
        mTitle = ""
        mSnippet = ""
        mCityName = ""
    }

    constructor(lat: Double, lng: Double, title: String, snippet: String, cityName : String) {
        mPosition = LatLng(lat, lng)
        mTitle = title
        mSnippet = snippet
        mCityName = cityName
    }

    override fun getPosition(): LatLng {
        return mPosition
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getSnippet(): String {
        return mSnippet
    }

    fun getCityName() : String {
        return mCityName
    }

}