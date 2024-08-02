package com.example.android_template

class Api {
    companion object{
        val Apikey2 ="c4ud8M0NJvtijONKtf5tBjidKl2g6wFD"
        val ApiKey = "SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy"
        var LocationKey ="353412"
            set(value) {
                field = value
                updateUrls()
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
            apiUrl = "https://dataservice.accuweather.com/currentconditions/v1/$LocationKey?apikey=$ApiKey&details=true"
            apiSunMoon = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/$LocationKey?apikey=$ApiKey&details=true"
            apiForecastHour = "https://dataservice.accuweather.com/forecasts/v1/hourly/12hour/$LocationKey?apikey=$Apikey2&details=true"
            apiForecastDay = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/$LocationKey?apikey=$Apikey2&details=true"
        }
    }
}