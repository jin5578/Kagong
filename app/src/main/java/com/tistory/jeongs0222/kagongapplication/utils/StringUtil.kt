package com.tistory.jeongs0222.kagongapplication.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

const val headerInterceptor = "headerInterceptor"
const val loggingInterceptor = "loggingInterceptor"

const val hostUrl = "http://jin5578.cafe24.com/KAGONG/"
const val accuWeatherUrl = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/"

val uid = FirebaseAuth.getInstance().uid!!