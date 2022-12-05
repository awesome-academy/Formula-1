package com.example.formula1.data.model

import com.example.formula1.R
import com.example.formula1.utils.RB_ID
import com.example.formula1.utils.FER_ID
import com.example.formula1.utils.MER_ID
import com.example.formula1.utils.ALP_ID
import com.example.formula1.utils.MCL_ID
import com.example.formula1.utils.ROM_ID
import com.example.formula1.utils.AST_ID
import com.example.formula1.utils.HAAS_ID
import com.example.formula1.utils.TAU_ID
import com.example.formula1.utils.WIL_ID

data class TeamUnique(val id: Int, val color: Int, val carImage: Int) {

    companion object {
        fun getTeamUnique(): List<TeamUnique> {
            return arrayListOf(
                TeamUnique(
                    RB_ID,
                    R.color.color_red_bull,
                    R.drawable.img_car_rb
                ),
                TeamUnique(
                    FER_ID,
                    R.color.color_ferrari,
                    R.drawable.img_car_fer
                ),
                TeamUnique(
                    MER_ID,
                    R.color.color_mercedes,
                    R.drawable.img_car_merc
                ),
                TeamUnique(
                    ALP_ID,
                    R.color.color_alpine,
                    R.drawable.img_car_alpine
                ),
                TeamUnique(
                    MCL_ID,
                    R.color.color_mclaren,
                    R.drawable.img_car_mcl
                ),
                TeamUnique(
                    ROM_ID,
                    R.color.color_romeo,
                    R.drawable.img_car_rom
                ),
                TeamUnique(
                    AST_ID,
                    R.color.color_aston,
                    R.drawable.img_car_ast
                ),
                TeamUnique(
                    HAAS_ID,
                    R.color.color_haas,
                    R.drawable.img_car_haas
                ),
                TeamUnique(
                    TAU_ID,
                    R.color.color_tauri,
                    R.drawable.img_car_tau
                ),
                TeamUnique(
                    WIL_ID,
                    R.color.color_williams,
                    R.drawable.img_car_wil
                ),
            )
        }
    }
}
