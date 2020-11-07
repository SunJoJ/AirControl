package com.example.aircontrol

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aircontrol.client.AQICNService
import com.example.aircontrol.client.AirQualityAPI
import com.example.aircontrol.databinding.ActivityMainBinding
import com.example.aircontrol.models.PollutionData
import com.example.aircontrol.utils.KeepStateNavigator
import com.example.aircontrol.utils.QualityRanges
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

//        theme.applyStyle(R.style.AppThemeDark, true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val portraitCollapsedRemoteView = RemoteViews(packageName, R.layout.notification_layout_collapsed)
        val expandedRemoteView = RemoteViews(packageName, R.layout.notification_layout_expanded)

        val request = AQICNService.buildService(AirQualityAPI::class.java)
        val call = request.getNearestCityPollutionData()
        call.enqueue(object : Callback<PollutionData> {
            override fun onResponse(call: Call<PollutionData>, response: Response<PollutionData>) {
                val cityData: PollutionData? = response.body()

                portraitCollapsedRemoteView.setTextViewText(R.id.aqiNumberNotification, cityData?.data?.aqi.toString() + " AQI")
                val indexColor = cityData?.data?.aqi?.let { QualityRanges.getIndexColor(it) }
                indexColor?.let { portraitCollapsedRemoteView.setImageViewResource(R.id.notificationColorImageView, it) }
                val currentDateTime = LocalDateTime.now()
                portraitCollapsedRemoteView.setTextViewText(R.id.notificationTime, currentDateTime.format(
                    DateTimeFormatter.ofPattern("HH:mm")))
                portraitCollapsedRemoteView.setTextViewText(R.id.addressNotification, cityData?.data?.city?.name)
                val desc = cityData?.data?.aqi?.let { QualityRanges.aqiIndexText(it) }
                portraitCollapsedRemoteView.setTextViewText(R.id.detailsNotification, desc)

                
                expandedRemoteView.setTextViewText(R.id.aqiNumberNotification, cityData?.data?.aqi.toString() + " AQI")
                indexColor?.let { expandedRemoteView.setImageViewResource(R.id.notificationColorImageView, it) }
                expandedRemoteView.setTextViewText(R.id.notificationTime, currentDateTime.format(
                    DateTimeFormatter.ofPattern("HH:mm")))
                expandedRemoteView.setTextViewText(R.id.addressNotification, cityData?.data?.city?.name)
                expandedRemoteView.setTextViewText(R.id.detailsNotification, desc)

//        val landscapeCollapsedRemoteView = RemoteViews(packageName, R.layout.notification_layout_collapsed)
//        val collapsedRemoteView = RemoteViews(landscapeCollapsedRemoteView, portraitCollapsedRemoteView)
//        val portraitExpandedRemoteViewWithListView = RemoteViews(packageName, R.layout.notification_expanded_portrait_with_list_view)
//        val landscapeExpandedRemoteView = RemoteViews(packageName, R.layout.notification_expanded_landscape)

                val CHANNEL_ID = "FOO_CHANNEL_ID"
                val name = "Foo channel name"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(CHANNEL_ID, name, importance)
                // Register the channel with the system
                (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)

                val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID).also {
                    it.setCustomContentView(portraitCollapsedRemoteView)
                    it.setCustomBigContentView(expandedRemoteView)
//            it.setCustomHeadsUpContentView(headUpRemoteView)
                    it.setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    it.setSmallIcon(R.drawable.logo) // Icon shown at the status bar
                    it.setPriority(NotificationCompat.PRIORITY_MAX)
                    it.setDefaults(Notification.DEFAULT_ALL)
                }

                val notification = notificationBuilder.build()
                val notificationId = System.currentTimeMillis().toInt() // Any thing you want to be an id
                (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(notificationId, notification)


            }

            override fun onFailure(call: Call<PollutionData>, t: Throwable) {

            }
        })




//        var navController = findNavController(R.id.nav_host_fragment)
//        bottom_nav.setupWithNavController(navController)

        val navController = findNavController(R.id.nav_host_fragment)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!
        val navigator = KeepStateNavigator(
            this,
            navHostFragment.childFragmentManager,
            R.id.nav_host_fragment
        )
        //navController.navigatorProvider += navigator
        //navController.setGraph(R.navigation.nav_graph)

        bottom_nav.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.mapFragment,
                R.id.profileFragment,
                R.id.ratingAndNewsFragment,
                R.id.settingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    private fun createNotificationChannel() {
        val name: CharSequence = "Air quality"
        val description = "Air quality notification"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("Air_quality_notification", name, importance)
        channel.description = description
        val notificationManager = getSystemService(
            NotificationManager::class.java
        )!!
        notificationManager.createNotificationChannel(channel)
    }


}