package com.example.tirewarehouse.data.enums

import androidx.annotation.DrawableRes
import com.example.tirewarehouse.R

enum class Season(@DrawableRes val imageRes: Int){
    SUMMER(R.drawable.summer),
    WINTER(R.drawable.winter),
    ALL_SEASON(R.drawable.all_season),
    AGRICULTURAL(R.drawable.all_season);

    companion object{
        fun FromString(value: String): Season{
            if(value == Season.SUMMER.name){
                return Season.SUMMER
            }else if(value == Season.WINTER.name){
                return Season.WINTER
            }else if(value == Season.ALL_SEASON.name){
                return Season.ALL_SEASON
            }else{
                return Season.AGRICULTURAL
            }
        }
    }
}