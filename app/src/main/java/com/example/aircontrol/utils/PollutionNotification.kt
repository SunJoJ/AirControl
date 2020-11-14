package com.example.aircontrol.utils

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.aircontrol.R
import com.example.aircontrol.client.AQICNService
import com.example.aircontrol.client.AirQualityAPI
import com.example.aircontrol.models.PollutionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PollutionNotification : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        //val view = View.inflate(context, R.layout.notification_layout_collapsed, null)
        View.inflate(context, R.layout.notification_layout_collapsed, null)

        val portraitCollapsedRemoteView = RemoteViews(
            context?.packageName,
            R.layout.notification_layout_collapsed
        )
        val expandedRemoteView = RemoteViews(
            context?.packageName,
            R.layout.notification_layout_expanded
        )


        val request = AQICNService.buildService(AirQualityAPI::class.java)
        val call = request.getNearestCityPollutionData()
        call.enqueue(object : Callback<PollutionData> {
            override fun onResponse(call: Call<PollutionData>, response: Response<PollutionData>) {
                val cityData: PollutionData? = response.body()


                portraitCollapsedRemoteView.setTextViewText(
                    R.id.aqiNumberNotification,
                    cityData?.data?.aqi.toString() + " AQI"
                )
                val indexColor = cityData?.data?.aqi?.let { QualityRanges.getIndexColor(it) }
                indexColor?.let {
                    portraitCollapsedRemoteView.setImageViewResource(
                        R.id.notificationColorImageView,
                        it
                    )
                }
                val currentDateTime = LocalDateTime.now()
                portraitCollapsedRemoteView.setTextViewText(
                    R.id.notificationTime, currentDateTime.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    )
                )
                portraitCollapsedRemoteView.setTextViewText(
                    R.id.addressNotification,
                    cityData?.data?.city?.name
                )
                val desc = cityData?.data?.aqi?.let { QualityRanges.aqiIndexText(it) }
                portraitCollapsedRemoteView.setTextViewText(R.id.detailsNotification, desc)


                expandedRemoteView.setTextViewText(
                    R.id.aqiNumberNotification,
                    cityData?.data?.aqi.toString() + " AQI"
                )
                indexColor?.let {
                    expandedRemoteView.setImageViewResource(
                        R.id.notificationColorImageView,
                        it
                    )
                }
                expandedRemoteView.setTextViewText(
                    R.id.notificationTime, currentDateTime.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    )
                )
                expandedRemoteView.setTextViewText(
                    R.id.addressNotification,
                    cityData?.data?.city?.name
                )
                expandedRemoteView.setTextViewText(R.id.detailsNotification, desc)

//        val landscapeCollapsedRemoteView = RemoteViews(packageName, R.layout.notification_layout_collapsed)
//        val collapsedRemoteView = RemoteViews(landscapeCollapsedRemoteView, portraitCollapsedRemoteView)
//        val portraitExpandedRemoteViewWithListView = RemoteViews(packageName, R.layout.notification_expanded_portrait_with_list_view)
//        val landscapeExpandedRemoteView = RemoteViews(packageName, R.layout.notification_expanded_landscape)

                val notification = NotificationCompat.Builder(context!!, "Air_quality_notification")
                    .setSmallIcon(R.drawable.logo)
                    .setCustomContentView(portraitCollapsedRemoteView)
                    .setCustomBigContentView(expandedRemoteView)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_ALL)
                    //.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .build()

                val notificationManagerCompat = NotificationManagerCompat.from(context)
                notificationManagerCompat.notify(10, notification)



            }

            override fun onFailure(call: Call<PollutionData>, t: Throwable) {

            }
        })


    }

}