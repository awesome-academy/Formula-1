package com.example.formula1.data.model

import com.example.formula1.data.model.finishpositionitem.Response

data class FinishPositionResponse(
    val response: List<Response>?,
    val results: Int?
)
