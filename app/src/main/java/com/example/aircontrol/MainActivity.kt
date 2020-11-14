package com.example.aircontrol

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
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
import com.example.aircontrol.utils.PollutionNotification
import com.example.aircontrol.utils.QualityRanges
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

//        theme.applyStyle(R.style.AppThemeDark, true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        createNotificationChannel()
        val intent = Intent(this@MainActivity, PollutionNotification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity, 10, intent, 0)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val firingCal = Calendar.getInstance()
        val currentCal = Calendar.getInstance()

        firingCal[Calendar.HOUR_OF_DAY] = 21
        firingCal[Calendar.MINUTE] = 30
        firingCal[Calendar.SECOND] = 30

        var intendedTime = firingCal.timeInMillis
        val currentTime = currentCal.timeInMillis

        if (intendedTime >= currentTime) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                firingCal.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } else {
            firingCal.add(Calendar.DAY_OF_MONTH, 1)
            intendedTime = firingCal.timeInMillis
            alarmManager.setRepeating(
                AlarmManager.RTC,
                intendedTime,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }


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