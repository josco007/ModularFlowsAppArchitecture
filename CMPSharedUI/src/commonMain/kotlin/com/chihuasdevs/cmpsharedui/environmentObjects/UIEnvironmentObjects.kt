package com.chihuasdevs.cmpsharedui.environmentObjects

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.chihuasdevs.cmpsharedui.themes.Theme


class UIEnvironmentObjects {

    var theme by mutableStateOf(Theme())

}