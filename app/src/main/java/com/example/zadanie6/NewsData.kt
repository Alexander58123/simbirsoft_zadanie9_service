package com.example.zadanie6

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsData(
    val id: Int,
    val imageId: Int,
    val title: String,
    val description: String,
    val data: String,
) : Parcelable
