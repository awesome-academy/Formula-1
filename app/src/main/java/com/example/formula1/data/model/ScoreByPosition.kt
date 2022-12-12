package com.example.formula1.data.model

import com.example.formula1.utils.SCORE_P1
import com.example.formula1.utils.SCORE_P2
import com.example.formula1.utils.SCORE_P3
import com.example.formula1.utils.SCORE_P4
import com.example.formula1.utils.SCORE_P5
import com.example.formula1.utils.SCORE_P6
import com.example.formula1.utils.SCORE_P7
import com.example.formula1.utils.SCORE_P8
import com.example.formula1.utils.SCORE_P9
import com.example.formula1.utils.SCORE_P10

fun getScoreByPosition(): List<Int> {
    return listOf(SCORE_P1, SCORE_P2, SCORE_P3, SCORE_P4, SCORE_P5, SCORE_P6, SCORE_P7, SCORE_P8, SCORE_P9, SCORE_P10)
}
