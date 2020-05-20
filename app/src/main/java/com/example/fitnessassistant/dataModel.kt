package com.example.fitnessassistant

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity
data class Movement(
    val moveName:String,
    val muscleName:String,
    //組數
    val setNum:Int,
    //次數
    val frequency:Int,
    //重量
    val weight:Int,
    //最後一組做到RPE是幾下
    val lastSet:Int
)


//val rPartList = arrayListOf<RoughlyPart>(
//    RoughlyPart("胸","胸大肌:上胸、中胸、下胸"),
//    RoughlyPart("背","闊背、菱形、斜方、豎脊肌"),
//    RoughlyPart("肩膀","三角肌:前束、中束、後束"),
//    RoughlyPart("三頭","外側頭、內側頭、長頭"),
//    RoughlyPart("二頭","長頭、短頭"),
//    RoughlyPart("臀腿","股四頭肌、腓腸肌、臀大肌"),
//    RoughlyPart("核心","腹直肌、腹外斜肌")
//)

val chestMoveList = arrayListOf<Movement>()
val backMoveList = arrayListOf<Movement>()
val shoulderMoveList = arrayListOf<Movement>()
val tricepsMoveList = arrayListOf<Movement>()
val bicepsMoveList = arrayListOf<Movement>()
val legsMoveList = arrayListOf<Movement>()
val coreMoveList = arrayListOf<Movement>()

