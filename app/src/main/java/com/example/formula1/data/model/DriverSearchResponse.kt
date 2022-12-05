package com.example.formula1.data.model

import com.example.formula1.data.model.driversearchitem.Response
import com.example.formula1.data.model.teamstanding.Parameters

data class  DriverSearchResponse(
    val get: String?,
    val parameters: Parameters?,
    val response: List<Response>?,
    val results: Int?
)
