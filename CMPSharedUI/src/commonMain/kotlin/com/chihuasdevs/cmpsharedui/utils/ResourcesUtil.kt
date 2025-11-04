package com.chihuasdevs.cmpsharedui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

expect object ResourcesUtil {
    @Composable
    fun getDrawableResourceId(resourceName: String): Any?
    @Composable
    fun loadPainter(resourceName: String?): Painter?
}
