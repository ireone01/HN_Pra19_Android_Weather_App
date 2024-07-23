package com.example.android_template

import android.view.View

data class Data(val ViewType : Int) {

    var  CurrentConditionList : List<CurrentCondition>? = null

    constructor(ViewType: Int , CurrentConditionList : List<CurrentCondition>): this(ViewType){
        this.CurrentConditionList =CurrentConditionList
    }
}


data class TemperatureUnit(
    val Label : String ,
    val Value: Double,
    val Unit: String,
)



data class CurrentCondition(
    val Label : String ,
    val Value: String,
    val Unit: String,
)

// SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy

// location key
//http://dataservice.accuweather.com/locations/v1/cities/search?apikey=SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy&q=Hanoi
//353412

//  dự báo hiện ta
//http://dataservice.accuweather.com/currentconditions/v1/location_key?apikey=SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy


// dự báo 5 ngày
// http://dataservice.accuweather.com/forecasts/v1/daily/5day/location_key?apikey=SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy

// Dự báo hàng giờ
// http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/location_key?apikey=SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy

// Cảnh báo thời tiết
//http://dataservice.accuweather.com/alerts/v1/location_key?apikey=SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy

//Chỉ số chất lượng không khí
//http://dataservice.accuweather.com/currentconditions/v1/261208/historical/location_key?apikey=SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy

//Radar
//http://dataservice.accuweather.com/radar/v1/location_key?apikey=SC8yMrpOtTb4IJA2MFxXHzlvrVMAxcNy