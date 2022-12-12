package com.example.formula1.data.model

import com.example.formula1.data.model.racingsearchitem.Response

data class RaceSearchResponse(
    val response: List<Response>,
    val results: Int
)
