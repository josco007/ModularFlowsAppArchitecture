package com.chihuasdevs.androidui.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext

object ResourcesUtil {

    fun getResourceId(context: Context, resourceName: String): Int {
        return context.resources.getIdentifier(
            resourceName,
            "drawable",
            context.packageName
        )
    }
}