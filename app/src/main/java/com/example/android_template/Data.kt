package com.example.android_template

data class Data(val ViewType : Int) {

}
data class RecyclerWeather(val temperature : Int,
                        val RealFeel : Int,
                        val wind : String,
    )

data class TemperatureUnit(
    val Value: Double,
    val Unit: String,
    val UnitType: Int
)
data class Temperature(
    val Metric: TemperatureUnit,
    val Imperial: TemperatureUnit
)
data class CurrentCondition(
    val LocalObservationDateTime: String,
    val EpochTime: Long,
    val WeatherText: String,
    val WeatherIcon: Int,
    val HasPrecipitation: Boolean,
    val PrecipitationType: String?,
    val IsDayTime: Boolean,
    val Temperature: Temperature,
    val MobileLink: String,
    val Link: String
)

// SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy


// dự báo hàng ngày
// http://dataservice.accuweather.com/forecasts/v1/daily/5day/{City}?apikey={Api_key}

// Dự báo hàng giờ
// http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/{City}?apikey={Api_key}

// Cảnh báo thời tiết
//http://dataservice.accuweather.com/alerts/v1/{City}?apikey={Api_key}

//Chỉ số chất lượng không khí
//http://dataservice.accuweather.com/currentconditions/v1/261208/historical/{City}?apikey={Api_key}

//Radar
//http://dataservice.accuweather.com/radar/v1/{City}?apikey={Api_key}