package com.example.aircontrol.utils

import com.example.aircontrol.R

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

    private fun isBetween(x: Int, lower: Int, upper: Int): Boolean {
        return x in lower..upper
    }

    fun  getIndexColor(x: Int): Int{
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