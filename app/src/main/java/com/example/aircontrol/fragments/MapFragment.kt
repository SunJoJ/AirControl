package com.example.aircontrol.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.aircontrol.R
import com.example.aircontrol.client.AQICNService
import com.example.aircontrol.client.AirQualityAPI
import com.example.aircontrol.databinding.FragmentMapBinding
import com.example.aircontrol.models.Cities
import com.example.aircontrol.models.MarkerItem
import com.example.aircontrol.models.PollutionData
import com.example.aircontrol.utils.CustomClusterRenderer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.fragment_city_data.view.*
import kotlinx.android.synthetic.main.fragment_map.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var binding : FragmentMapBinding
    private lateinit var mClusterManager: ClusterManager<MarkerItem>
    val listOfPollutionData: ArrayList<PollutionData> = arrayListOf()
    private lateinit var customClusterRenderer: CustomClusterRenderer
    private val suggestions: ArrayList<String> = arrayListOf()
    private var mAdapter: SimpleCursorAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        //activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val from = arrayOf("cityName")
        val to = intArrayOf(android.R.id.text1)
        mAdapter = SimpleCursorAdapter(
            activity,
            android.R.layout.simple_list_item_1,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )

//        val searchView = searchNavigationMenu.menu.findItem(R.id.action_search).actionView
//        searchView.suggestionsAdapter = mAdapter
//        searchView.setIconifiedByDefault(false)
//
//
//        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
//            override fun onSuggestionClick(position: Int): Boolean {
//                val cursor = mAdapter.getItem(position)
//                val txt = cursor.getString(cursor.getColumnIndex("cityName"))
//                searchView.setQuery(txt, true)
//                return true
//            }
//
//            override fun onSuggestionSelect(position: Int): Boolean {
//                return true
//            }
//        })


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mClusterManager = ClusterManager(context, mMap)
        customClusterRenderer = context?.let { CustomClusterRenderer(it, mMap, mClusterManager) }!!
        mClusterManager.renderer = customClusterRenderer
        mMap.setOnCameraIdleListener(mClusterManager)
        mMap.setOnMarkerClickListener(mClusterManager)

        val request = AQICNService.buildService(AirQualityAPI::class.java)
        for(city in Cities.allCitiesArray) {
            val call = request.getCityPollutionData(city.cityName)
            call.enqueue(object : Callback<PollutionData> {
                override fun onResponse(
                    call: Call<PollutionData>,
                    response: Response<PollutionData>
                ) {
                    //Log.d("test", response.body().toString())
                    val data = response.body()
                    if (data != null) {
                        mClusterManager.addItem(
                            MarkerItem(
                                data.data.city.geo[0],
                                data.data.city.geo[1],
                                data.data.idx.toString(),
                                data.data.aqi.toString(),
                                city.cityName
                            )
                        )
                        listOfPollutionData.add(data)
                        suggestions.add(data.data.city.name)
                        mClusterManager.cluster()
                    }
                }

                override fun onFailure(call: Call<PollutionData>, t: Throwable) {
                    Log.d("test", t.message)
                }
            })
        }


        mClusterManager.let { it ->
            it.setOnClusterItemClickListener {
            val cityData = listOfPollutionData.filter { x -> x.data.idx.toString() == it.title }
            val bundle = bundleOf(
                "cityName" to cityData[0].data.city.name,
                "cityData" to cityData[0],
                "cityNameForRequest" to it.getCityName()
            )
            view?.findNavController()?.navigate(
                R.id.action_mapFragment_to_markerDetailsFragment,
                bundle
            )

            true
            }
        }




    }

}