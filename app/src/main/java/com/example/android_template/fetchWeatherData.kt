package com.example.android_template

import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

suspend fun fetchWeatherData(apiUrl: String): List<CurrentCondition> = withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(apiUrl)
                .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                        val gson = Gson()
                        val listType = object : TypeToken<List<JsonObject>>() {}.type
                        val jsonArray: List<JsonObject> = gson.fromJson(responseBody, listType)

                        if (jsonArray.isNotEmpty()) {
                                val currentConditionJson = jsonArray[0]

                                return@withContext listOf(
                                        CurrentCondition(
                                                "Nhiệt độ",
                                                currentConditionJson.getAsJsonObject("Temperature")
                                                        .getAsJsonObject("Metric")
                                                        .get("Value").asString,
                                                "°" + currentConditionJson.getAsJsonObject("Temperature")
                                                        .getAsJsonObject("Metric")
                                                        .get("Unit").asString
                                        ),
                                        CurrentCondition(
                                                "Nhiệt độ cảm nhận",
                                                currentConditionJson.getAsJsonObject("RealFeelTemperature")
                                                        .getAsJsonObject("Metric")
                                                        .get("Value").asString,
                                                "°" + currentConditionJson.getAsJsonObject("RealFeelTemperature")
                                                        .getAsJsonObject("Metric")
                                                        .get("Unit").asString
                                        ),
                                        CurrentCondition(
                                                "Tốc độ gió",
                                                currentConditionJson.getAsJsonObject("Wind")
                                                        .getAsJsonObject("Speed")
                                                        .getAsJsonObject("Metric")
                                                        .get("Value").asString,
                                                " " + currentConditionJson.getAsJsonObject("Wind")
                                                        .getAsJsonObject("Speed")
                                                        .getAsJsonObject("Metric")
                                                        .get("Unit").asString
                                        ),
                                        CurrentCondition(
                                                "Tốc độ gió lớn nhất",
                                                currentConditionJson.getAsJsonObject("WindGust")
                                                        .getAsJsonObject("Speed")
                                                        .getAsJsonObject("Metric")
                                                        .get("Value").asString,
                                                " " + currentConditionJson.getAsJsonObject("WindGust")
                                                        .getAsJsonObject("Speed")
                                                        .getAsJsonObject("Metric")
                                                        .get("Unit").asString
                                        ),
                                        CurrentCondition(
                                                "Độ ẩm",
                                                currentConditionJson.get("RelativeHumidity").asString,
                                                "%"
                                        ),
                                        CurrentCondition(
                                                "Độ ẩm trong nhà",
                                                currentConditionJson.get("IndoorRelativeHumidity").asString,
                                                "%"
                                        )
                                )
                        }
                }
        }
        return@withContext emptyList<CurrentCondition>()
}

suspend fun fetSunMoon(apiUrl: String ) : List<SunMoon>  = withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder().url(apiUrl).build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                        val gson = Gson()
                        val listType = object : TypeToken<List<JsonObject>>() {}.type
                        val jsonArray = gson.fromJson(responseBody, JsonObject :: class.java)
                        val Sunmoon  = jsonArray.getAsJsonArray("DailyForecasts")
                        if (Sunmoon!=null && Sunmoon.size()>0) {
                                val SunMoonJson = Sunmoon[0].asJsonObject
                                val Sun = SunMoonJson.getAsJsonObject("Sun")
                                val Moon = SunMoonJson.getAsJsonObject("Moon")

                                val SunRise =Sun.get("Rise").asString
                                val SunSet = Sun.get("Set").asString
                                val MoonRise = Moon.get("Rise").asString
                                val MoonSet = Moon.get("Set").asString

                                return@withContext listOf(SunMoon("Sun",SunRise,SunSet), SunMoon("Moon",MoonRise,MoonSet))
                        }
                }
        }
        return@withContext emptyList<SunMoon>()
}
