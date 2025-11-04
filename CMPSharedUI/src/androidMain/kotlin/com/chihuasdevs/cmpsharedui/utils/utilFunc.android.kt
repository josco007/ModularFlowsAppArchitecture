package com.chihuasdevs.cmpsharedui.utils

import androidx.compose.runtime.Composable

@Composable
actual fun BackHandler(onBack: () -> Unit) {
    // En Compose Multiplatform, si estás en Android, esto funciona:
    androidx.activity.compose.BackHandler(onBack = onBack)
}