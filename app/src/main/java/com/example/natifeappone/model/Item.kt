package com.example.natifeappone.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val id:Int, val name: String, val description: String) : Parcelable
