package com.fitpeo.urnishassignment.utils

import android.content.Context
import android.view.LayoutInflater

object Utility {

    val ITEM_TITLE = "item_title"
    val ITEM_IMAGE_URL = "item_image_url"

    inline val Context.inflater: LayoutInflater
        get() = LayoutInflater.from(this)

}