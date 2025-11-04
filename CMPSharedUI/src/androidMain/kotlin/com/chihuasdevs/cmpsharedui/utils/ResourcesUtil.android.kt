package com.chihuasdevs.cmpsharedui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

actual object ResourcesUtil {
    @Composable
    actual fun getDrawableResourceId(resourceName: String): Any? {
        val context = LocalContext.current
        return context.resources.getIdentifier(
            resourceName,
            "drawable",
            context.packageName
        )
    }

    @Composable
    actual fun loadPainter(resourceName: String?): Painter? {
        if (resourceName == null) return null
        val context = LocalContext.current
        val resId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        return if (resId != 0) painterResource(resId) else null
    }
}