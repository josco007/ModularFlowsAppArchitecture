package com.chihuasdevs.androidui.environmentObjects

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.chihuasdevs.androidui.themes.Theme

class UIEnvironmentObjects {

    var theme by mutableStateOf(Theme())

}