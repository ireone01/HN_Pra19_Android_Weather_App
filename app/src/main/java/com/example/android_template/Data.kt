package com.example.android_template

import android.view.View

data class Data(val ViewType : Int) {

    var  CurrentConditionList : List<CurrentCondition>? = null

    constructor(ViewType: Int , CurrentConditionList : List<CurrentCondition>): this(ViewType){
        this.CurrentConditionList =CurrentConditionList
    }
}


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
//  dự báo hiện ta
//http://dataservice.accuweather.com/currentconditions/v1/{location_key}?apikey={Api_key}


// dự báo 5 ngày
// http://dataservice.accuweather.com/forecasts/v1/daily/5day/{location_key}?apikey={Api_key}

// Dự báo hàng giờ
// http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/{location_key}?apikey={Api_key}

// Cảnh báo thời tiết
//http://dataservice.accuweather.com/alerts/v1/{location_key}?apikey={Api_key}

//Chỉ số chất lượng không khí
//http://dataservice.accuweather.com/currentconditions/v1/261208/historical/{location_key}?apikey={Api_key}

//Radar
//http://dataservice.accuweather.com/radar/v1/{location_key}?apikey={Api_key}