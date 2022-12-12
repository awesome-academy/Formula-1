package com.example.formula1.data.model

import com.example.formula1.data.model.fastestlapitem.Response

data class FastestLapResponse(
    val response: List<Response>,
    val results: Int
)
