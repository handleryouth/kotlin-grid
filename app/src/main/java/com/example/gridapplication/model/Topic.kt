package com.example.gridapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes  val stringResourceId: Int,
    val likes: Int,
    @DrawableRes val imageResourceId: Int
){}