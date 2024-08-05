package com.example.android_template.Api

import android.util.Log

class Api {
    companion object{
        val Apikey2 ="c4ud8M0NJvtijONKtf5tBjidKl2g6wFD"
        val Apikey = "SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy"
        val Apikey3 ="j8aiCZItQzDyTe5Oi28vOUTitlkSjXH6"
        var isInitialized = false
        var LocationKey ="353412"
            set(value) {
                field = value

                updateUrls()
                Log.i("Api_adfssaf", "$LocationKey")

                if (isInitialized ){
                    onLocationKeyUpdated()
                }
                isInitialized = true
            }

        var apiUrl: String = ""
            private set
        var apiSunMoon: String = ""
            private set
        var apiForecastHour: String = ""
            private set
        var apiForecastDay: String = ""
            private set

        init {
            updateUrls()
        }

        private fun updateUrls() {
            apiUrl = "https://dataservice.accuweather.com/currentconditions/v1/$LocationKey?apikey=$Apikey3&details=true"
            apiSunMoon = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/$LocationKey?apikey=$Apikey3&details=true"
            apiForecastHour = "https://dataservice.accuweather.com/forecasts/v1/hourly/12hour/$LocationKey?apikey=$Apikey3&details=true"
            apiForecastDay = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/$LocationKey?apikey=$Apikey3&details=true"
        }
        var onLocationKeyUpdated: () -> Unit = {}
    }
}