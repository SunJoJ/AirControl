package com.example.aircontrol

import com.google.gson.annotations.SerializedName

data class PollutionData(
    @SerializedName("status") val status : String,
    @SerializedName("data") val data : Data
)

data class Data (

    @SerializedName("aqi") val aqi : Int,
    @SerializedName("idx") val idx : Int,
    @SerializedName("attributions") val attributions : List<Attributions>,
    @SerializedName("city") val city : City,
    @SerializedName("dominentpol") val dominentpol : String,
    @SerializedName("iaqi") val iaqi : Iaqi,
    @SerializedName("time") val time : Time,
    @SerializedName("forecast") val forecast : Forecast,
    @SerializedName("debug") val debug : Debug
)

data class Daily (

    @SerializedName("o3") val o3 : List<O3>,
    @SerializedName("pm10") val pm10 : List<Pm10>,
    @SerializedName("pm25") val pm25 : List<Pm25>,
    @SerializedName("uvi") val uvi : List<Uvi>
)

data class Co (

    @SerializedName("v") val v : Double
)

data class Attributions (

    @SerializedName("url") val url : String,
    @SerializedName("name") val name : String
)

data class City (

    @SerializedName("geo") val geo : List<Double>,
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)

data class W (
    @SerializedName("v") val v : Double
)

data class Uvi (

    @SerializedName("avg") val avg : Int,
    @SerializedName("day") val day : String,
    @SerializedName("max") val max : Int,
    @SerializedName("min") val min : Int
)

data class Time (

    @SerializedName("s") val s : String,
    @SerializedName("tz") val tz : String,
    @SerializedName("v") val v : Int,
    @SerializedName("iso") val iso : String
)

data class T (

    @SerializedName("v") val v : Int
)

data class So2 (

    @SerializedName("v") val v : Double
)

data class Pm25 (

    @SerializedName("avg") val avg : Int,
    @SerializedName("day") val day : String,
    @SerializedName("max") val max : Int,
    @SerializedName("min") val min : Int
)

data class Pm10 (

    @SerializedName("avg") val avg : Int,
    @SerializedName("day") val day : String,
    @SerializedName("max") val max : Int,
    @SerializedName("min") val min : Int
)

data class P (

    @SerializedName("v") val v : Int
)

data class O3 (

    @SerializedName("avg") val avg : Int,
    @SerializedName("day") val day : String,
    @SerializedName("max") val max : Int,
    @SerializedName("min") val min : Int
)

data class No2 (

    @SerializedName("v") val v : Double
)

data class Iaqi (

    @SerializedName("co") val co : Co,
    @SerializedName("h") val h : H,
    @SerializedName("no2") val no2 : No2,
    @SerializedName("o3") val o3 : O3,
    @SerializedName("p") val p : P,
    @SerializedName("pm10") val pm10 : Pm10,
    @SerializedName("pm25") val pm25 : Pm25,
    @SerializedName("so2") val so2 : So2,
    @SerializedName("t") val t : T,
    @SerializedName("w") val w : W
)

data class H (

    @SerializedName("v") val v : Int
)


data class Forecast (

    @SerializedName("daily") val daily : Daily
)

data class Debug (

    @SerializedName("sync") val sync : String
)