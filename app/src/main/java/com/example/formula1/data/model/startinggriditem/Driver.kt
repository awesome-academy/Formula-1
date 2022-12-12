package com.example.formula1.data.model.startinggriditem

import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("abbr")
    val nameTag: String?,
    val id: Int?,
    val image: String?,
    val name: String?,
    val number: Int?
)
