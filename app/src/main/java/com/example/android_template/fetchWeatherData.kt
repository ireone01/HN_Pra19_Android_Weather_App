package com.example.android_template

import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

fun fetchWeatherData(apiUrl: String , callBack :(List<CurrentCondition>) ->Unit){
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(apiUrl)
                .build()

        client.newCall(request).enqueue(object : okhttp3.Callback{
                override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()

                }

                override fun onResponse(call: Call, response: Response) {
                        if(response.isSuccessful){
                                val responseBody = response.body?.string()
                                if(responseBody != null){
                                        val gson = Gson()
                                        val listType = object  : TypeToken<List<JsonObject>>() {}.type
                                        val jsonArray : List<JsonObject> = gson.fromJson(responseBody,listType)


                                        if(jsonArray.isNotEmpty()){
                                                val currentConditionJson = jsonArray[0]

                                                val currentConditions = listOf(
                                                        CurrentCondition(
                                                                "Nhiệt độ",
                                                                currentConditionJson.getAsJsonObject("Temperature")
                                                                        .getAsJsonObject("Metric")
                                                                        .get("Value").asString,
                                                                "°"+currentConditionJson.getAsJsonObject("Temperature")
                                                                        .getAsJsonObject("Metric")
                                                                        .get("Unit").asString
                                                        ),
                                                        CurrentCondition(
                                                                "Nhiệt độ cảm nhận",
                                                                currentConditionJson.getAsJsonObject("RealFeelTemperature")
                                                                        .getAsJsonObject("Metric")
                                                                        .get("Value").asString,
                                                                "°"+currentConditionJson.getAsJsonObject("RealFeelTemperature")
                                                                        .getAsJsonObject("Metric")
                                                                        .get("Unit").asString
                                                        ),
                                                        CurrentCondition(
                                                                "Tốc độ gió ",
                                                                currentConditionJson.getAsJsonObject("Wind")
                                                                        .getAsJsonObject("Speed")
                                                                        .getAsJsonObject("Metric")
                                                                        .get("Value").asString,
                                                                " "+ currentConditionJson.getAsJsonObject("Wind")
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
                                                callBack(currentConditions)
                                        }

                                }
                        }else{
                                println("Error: ${response.code} - ${response.message}")
                        }
                }

        })
}