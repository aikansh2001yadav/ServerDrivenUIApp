package com.example.fampayassignmentapp.commons

import android.content.res.Resources
import android.util.TypedValue

object Utils {
    val Float.toPx
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
}