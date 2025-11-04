package com.chihuasdevs.cmpsharedui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

actual object ResourcesUtil {
    @Composable
    actual fun getDrawableResourceId(resourceName: String): Any? {
        TODO("Not yet implemented")
    }

    @Composable
    actual fun loadPainter(resourceName: String?): Painter? {
        // TODO: Implementar soporte de imágenes iOS si las agregas en el futuro
        return null
    }
}