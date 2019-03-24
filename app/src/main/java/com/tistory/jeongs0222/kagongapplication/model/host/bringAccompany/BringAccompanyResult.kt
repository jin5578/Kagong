package com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany


data class BringAccompanyResult(
    val order: Int,
    val nickname: String,
    val title: String,
    val content: String,
    val written_time: String,
    val area: String,
    val meeting_date: String,
    val link: String
)