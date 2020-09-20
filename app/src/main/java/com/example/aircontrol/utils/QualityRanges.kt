package com.example.aircontrol.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.aircontrol.R
import com.example.aircontrol.models.CurrentData
import com.example.aircontrol.models.Daily
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

object QualityRanges {

    private const val S02_VERY_GOOD = 50
    private const val S02_GOOD = 100
    private const val S02_MODERATE = 200
    private const val S02_PASSABLE = 350
    private const val S02_BAD = 500
    private const val S02_VERY_BAD = 1000

    private const val N02_VERY_GOOD = 40
    private const val N02_GOOD = 100
    private const val N02_MODERATE = 150
    private const val N02_PASSABLE = 200
    private const val N02_BAD = 400
    private const val N02_VERY_BAD = 1000

    private const val CO_VERY_GOOD = 2000
    private const val CO_GOOD = 6000
    private const val CO_MODERATE = 10000
    private const val CO_PASSABLE = 14000
    private const val CO_BAD = 20000
    private const val CO_VERY_BAD = 100000

    private const val PM10_VERY_GOOD = 20
    private const val PM10_GOOD = 60
    private const val PM10_MODERATE = 100
    private const val PM10_PASSABLE = 140
    private const val PM10_BAD = 200
    private const val PM10_VERY_BAD = 1000

    private const val PM25_VERY_GOOD = 12
    private const val PM25_GOOD = 36
    private const val PM25_MODERATE = 60
    private const val PM25_PASSABLE = 84
    private const val PM25_BAD = 120
    private const val PM25_VERY_BAD = 1000

    private const val O3_VERY_GOOD = 24
    private const val O3_GOOD = 70
    private const val O3_MODERATE = 120
    private const val O3_PASSABLE = 160
    private const val O3_BAD = 240
    private const val O3_VERY_BAD = 1000

    private const val C6H6_VERY_GOOD = 5
    private const val C6H6_GOOD = 10
    private const val C6H6_MODERATE = 15
    private const val C6H6_PASSABLE = 20
    private const val C6H6_BAD = 50
    private const val C6H6_VERY_BAD = 1000

    private const val veryGood = 25
    private const val good = 50
    private const val moderate = 100
    private const val passable = 125
    private const val bad = 150
    private const val veryBad = 200

    fun paintChart(values: Daily, context: Context, parameter: String, barChart: BarChart): BarData {

        when(parameter) {
            "o3" -> {
                val listOfData = values.o3

                val bars = ArrayList<IBarDataSet>()
                val time = ArrayList<String>()
                for ((num, x) in listOfData.withIndex()) {
                    val entries = ArrayList<BarEntry>()

                    time.add(x.day)
                    entries.add(BarEntry(num.toFloat(), x.avg.toFloat()))
                    when {
                        isBetween(x.avg, 0, O3_VERY_GOOD) -> {
                            val dataSet = BarDataSet(entries, "Green")
                            val color = ContextCompat.getColor(context, R.color.darkGreen)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, O3_VERY_GOOD + 1, O3_GOOD) -> {
                            val dataSet = BarDataSet(entries, "Green")
                            val color = ContextCompat.getColor(context, R.color.lightGreen)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, O3_GOOD + 1, O3_MODERATE) -> {
                            val dataSet = BarDataSet(entries, "Yellow")
                            val color = ContextCompat.getColor(context, R.color.yellow)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, O3_MODERATE + 1, O3_PASSABLE) -> {
                            val dataSet = BarDataSet(entries, "Yellow")
                            val color = ContextCompat.getColor(context, R.color.orange)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, O3_PASSABLE + 1, O3_BAD) -> {
                            val dataSet = BarDataSet(entries, "Red")
                            val color = ContextCompat.getColor(context, R.color.lightRed)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, O3_BAD + 1, O3_VERY_BAD) -> {
                            val dataSet = BarDataSet(entries, "Red")
                            val color = ContextCompat.getColor(context, R.color.darkRed)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                    }
                }
                val xAxis = barChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM

                val formatter = IndexAxisValueFormatter(time)
                xAxis.granularity = 1f
                xAxis.valueFormatter = formatter

                return BarData(bars)
            }
            "pm10" -> {
                val listOfData = values.pm10

                val bars = ArrayList<IBarDataSet>()
                val time = ArrayList<String>()
                for ((num, x) in listOfData.withIndex()) {
                    val entries = ArrayList<BarEntry>()

                    time.add(x.day)
                    entries.add(BarEntry(num.toFloat(), x.avg.toFloat()))
                    when {
                        isBetween(x.avg, 0, PM10_VERY_GOOD) -> {
                            val dataSet = BarDataSet(entries, "Green")
                            val color = ContextCompat.getColor(context, R.color.darkGreen)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM10_VERY_GOOD + 1, PM10_GOOD) -> {
                            val dataSet = BarDataSet(entries, "Green")
                            val color = ContextCompat.getColor(context, R.color.lightGreen)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM10_GOOD + 1, PM10_MODERATE) -> {
                            val dataSet = BarDataSet(entries, "Yellow")
                            val color = ContextCompat.getColor(context, R.color.yellow)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM10_MODERATE + 1, PM10_PASSABLE) -> {
                            val dataSet = BarDataSet(entries, "Yellow")
                            val color = ContextCompat.getColor(context, R.color.orange)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM10_PASSABLE + 1, PM10_BAD) -> {
                            val dataSet = BarDataSet(entries, "Red")
                            val color = ContextCompat.getColor(context, R.color.lightRed)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM10_BAD + 1, PM10_VERY_BAD) -> {
                            val dataSet = BarDataSet(entries, "Red")
                            val color = ContextCompat.getColor(context, R.color.darkRed)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                    }
                }
                val xAxis = barChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM

                val formatter = IndexAxisValueFormatter(time)
                xAxis.granularity = 1f
                xAxis.valueFormatter = formatter

                return BarData(bars)
            }
            "pm25" -> {
                val listOfData = values.pm25

                val bars = ArrayList<IBarDataSet>()
                val time = ArrayList<String>()
                for ((num, x) in listOfData.withIndex()) {
                    val entries = ArrayList<BarEntry>()

                    time.add(x.day)
                    entries.add(BarEntry(num.toFloat(), x.avg.toFloat()))
                    when {
                        isBetween(x.avg, 0, PM25_VERY_GOOD) -> {
                            val dataSet = BarDataSet(entries, "Green")
                            val color = ContextCompat.getColor(context, R.color.darkGreen)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM25_VERY_GOOD + 1, PM25_GOOD) -> {
                            val dataSet = BarDataSet(entries, "Green")
                            val color = ContextCompat.getColor(context, R.color.lightGreen)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM25_GOOD + 1, PM25_MODERATE) -> {
                            val dataSet = BarDataSet(entries, "Yellow")
                            val color = ContextCompat.getColor(context, R.color.yellow)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM25_MODERATE + 1, PM25_PASSABLE) -> {
                            val dataSet = BarDataSet(entries, "Yellow")
                            val color = ContextCompat.getColor(context, R.color.orange)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM25_PASSABLE + 1, PM25_BAD) -> {
                            val dataSet = BarDataSet(entries, "Red")
                            val color = ContextCompat.getColor(context, R.color.lightRed)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                        isBetween(x.avg, PM25_BAD + 1, PM25_VERY_BAD) -> {
                            val dataSet = BarDataSet(entries, "Red")
                            val color = ContextCompat.getColor(context, R.color.darkRed)
                            dataSet.color = color
                            bars.add(dataSet)
                        }
                    }
                }
                val xAxis = barChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM

                val formatter = IndexAxisValueFormatter(time)
                xAxis.granularity = 1f
                xAxis.valueFormatter = formatter

                return BarData(bars)
            }
            else -> return BarData()
        }
    }


    private fun isBetween(x: Int?, lower: Int, upper: Int): Boolean {
        return x in lower..upper
    }

    fun getParameterColor(currentData: CurrentData) : Int {
        val x = currentData.actualValue?.toInt()
        when (currentData.pollutantTitle) {
            "pm10" -> {
                if(isBetween(x, 0, PM10_VERY_GOOD))
                    return R.drawable.dark_green_rounded_corner
                if(isBetween(x, PM10_VERY_GOOD, PM10_GOOD))
                    return R.drawable.light_green_rounded_corner
                if(isBetween(x, PM10_GOOD, PM10_MODERATE))
                    return R.drawable.yellow_rounded_corner
                if(isBetween(x, PM10_MODERATE, PM10_PASSABLE))
                    return R.drawable.orange_rounded_corner
                if(isBetween(x, PM10_PASSABLE, PM10_BAD))
                    return R.drawable.red_rounded_corner
                if(isBetween(x, PM10_BAD, PM10_VERY_BAD))
                    return R.drawable.dark_red_roudned_corner
                return if(isBetween(x, PM10_VERY_BAD, 9999))
                    R.drawable.purple_rounded_corner
                else
                    R.drawable.rounded_corner_gray
            }
            "pm25" -> {
                if(isBetween(x, 0, PM25_VERY_GOOD))
                    return R.drawable.dark_green_rounded_corner
                if(isBetween(x, PM25_VERY_GOOD, PM25_GOOD))
                    return R.drawable.light_green_rounded_corner
                if(isBetween(x, PM25_GOOD, PM25_MODERATE))
                    return R.drawable.yellow_rounded_corner
                if(isBetween(x, PM25_MODERATE, PM25_PASSABLE))
                    return R.drawable.orange_rounded_corner
                if(isBetween(x, PM25_PASSABLE, PM25_BAD))
                    return R.drawable.red_rounded_corner
                if(isBetween(x, PM25_BAD, PM25_VERY_BAD))
                    return R.drawable.dark_red_roudned_corner
                return if(isBetween(x, PM25_VERY_BAD, 9999))
                    R.drawable.purple_rounded_corner
                else
                    R.drawable.rounded_corner_gray
            }
            "co" -> {
                if(isBetween(x, 0, CO_VERY_GOOD))
                    return R.drawable.dark_green_rounded_corner
                if(isBetween(x, CO_VERY_GOOD, CO_GOOD))
                    return R.drawable.light_green_rounded_corner
                if(isBetween(x, CO_GOOD, CO_MODERATE))
                    return R.drawable.yellow_rounded_corner
                if(isBetween(x, CO_MODERATE, CO_PASSABLE))
                    return R.drawable.orange_rounded_corner
                if(isBetween(x, CO_PASSABLE, CO_BAD))
                    return R.drawable.red_rounded_corner
                if(isBetween(x, CO_BAD, CO_VERY_BAD))
                    return R.drawable.dark_red_roudned_corner
                return if(isBetween(x, CO_VERY_BAD, 9999))
                    R.drawable.purple_rounded_corner
                else
                    R.drawable.rounded_corner_gray
            }
            "no2" -> {
                if(isBetween(x, 0, N02_VERY_GOOD))
                    return R.drawable.dark_green_rounded_corner
                if(isBetween(x, N02_VERY_GOOD, N02_GOOD))
                    return R.drawable.light_green_rounded_corner
                if(isBetween(x, N02_GOOD, N02_MODERATE))
                    return R.drawable.yellow_rounded_corner
                if(isBetween(x, N02_MODERATE, N02_PASSABLE))
                    return R.drawable.orange_rounded_corner
                if(isBetween(x, N02_PASSABLE, N02_BAD))
                    return R.drawable.red_rounded_corner
                if(isBetween(x, N02_BAD, N02_VERY_BAD))
                    return R.drawable.dark_red_roudned_corner
                return if(isBetween(x, N02_VERY_BAD, 9999))
                    R.drawable.purple_rounded_corner
                else
                    R.drawable.rounded_corner_gray
            }
            else -> return R.drawable.rounded_corner_gray
        }
    }


    fun getIndexColor(x: Int): Int{
        if(isBetween(x, 0, veryGood))
            return R.drawable.dark_green_rounded_corner
        if(isBetween(x, veryGood, good))
            return R.drawable.light_green_rounded_corner
        if(isBetween(x, good, moderate))
            return R.drawable.yellow_rounded_corner
        if(isBetween(x, moderate, passable))
            return R.drawable.orange_rounded_corner
        if(isBetween(x, passable, bad))
            return R.drawable.red_rounded_corner
        if(isBetween(x, bad, veryBad))
            return R.drawable.dark_red_roudned_corner
        return if(isBetween(x, veryBad, 9999))
            R.drawable.purple_rounded_corner
        else
            R.drawable.rounded_corner_gray
    }

    fun  getIconMarker(x: Int): Int {
        if(isBetween(x, 0, veryGood))
            return R.drawable.dark_green_marker
        if(isBetween(x, veryGood, good))
            return R.drawable.light_green_marker
        if(isBetween(x, good, moderate))
            return R.drawable.yellow_marker
        if(isBetween(x, moderate, passable))
            return R.drawable.orange_marker
        if(isBetween(x, passable, bad))
            return R.drawable.light_red_marker
        if(isBetween(x, bad, veryBad))
            return R.drawable.dark_red_marker
        return if(isBetween(x, veryBad, 9999))
            R.drawable.purple_marker
        else
            R.drawable.yellow_marker
    }



}