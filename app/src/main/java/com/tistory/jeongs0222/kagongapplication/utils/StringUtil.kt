package com.tistory.jeongs0222.kagongapplication.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

const val headerInterceptor = "headerInterceptor"
const val loggingInterceptor = "loggingInterceptor"

const val hostUrl = "http://jin5578.cafe24.com/KAGONG/"

val uid = FirebaseAuth.getInstance().uid!!