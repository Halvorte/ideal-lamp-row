package com.example.rowapp.dataClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

typealias GameState = @RawValue List<List<Any>>

@Parcelize
data class Game (val players:MutableList<String>, val gameId:String, val state:GameState): Parcelable