package com.moviedetail.utils

import android.app.Activity
import android.util.DisplayMetrics

object Utils {


    fun getDeviceWidth(context: Activity): Int {
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }


    fun getActualHeight(actualWidth: Int, aspectH: Int, aspectW: Int): Int {
        return aspectH * actualWidth / aspectW
    }
}
