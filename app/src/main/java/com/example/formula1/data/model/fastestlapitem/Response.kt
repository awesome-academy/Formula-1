package com.example.formula1.data.model.fastestlapitem

import com.example.formula1.data.model.startinggriditem.Driver
import com.example.formula1.data.model.startinggriditem.Team

data class Response(
    val driver: Driver?,
    val lap: Int?,
    val position: Int,
    val team: Team?,
    val time: String?
)
