package com.example.saysco.data.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    val name: String,
    val id: Int,
    val email: String,
    val token: String,
)