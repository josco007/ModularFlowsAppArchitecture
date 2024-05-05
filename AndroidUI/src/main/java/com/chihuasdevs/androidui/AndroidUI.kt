package com.chihuasdevs.androidui

import com.chihuasdevs.androidui.uiFlows.appFlow.UIAppFlow
import com.chihuasdevs.androidui.appScreens.appView.AppViewModel
import com.chihuasdevs.androidui.environmentObjects.UIEnvironmentObjects
import com.chihuasdevs.androidui.themes.Theme
import com.chihuasdevs.androidui.themes.Theme1
import com.chihuasdevs.androidui.themes.Theme2

object AndroidUI {

    class ThemeConf {
        enum class ThemeType {
            DEFAULT_THEME,
            THEME1,
            THEME2,
            CUSTOM_THEME
        }

        var themeType: ThemeType = ThemeType.DEFAULT_THEME
        var theme: Theme? = null

        constructor(themeType: ThemeType, theme: Theme? = null) {
            this.themeType = themeType
            this.theme = theme
        }

        constructor() {}
    }

    private var uiAppFlow: UIAppFlow? = null
    private lateinit var uiEnvironmentObjects: UIEnvironmentObjects
    private var customTheme: Theme? = null

    fun initUIKit(themeConf: ThemeConf){
        uiEnvironmentObjects = UIEnvironmentObjects()
        changeTheme(themeConf)
    }

    fun setUIAppFLow(uiAppFlow: UIAppFlow?) {
        this.uiAppFlow = uiAppFlow
    }

    fun changeTheme(themeConf: ThemeConf) {
        themeConf.theme?.let { this.customTheme = it }
        this.uiEnvironmentObjects.theme = this.getTheme(themeType = themeConf.themeType)
    }

    fun getAppViewModel(): AppViewModel? {
        return this.uiAppFlow?.getAppViewModel()
    }

    fun getUIEnvObjects(): UIEnvironmentObjects {
        return uiEnvironmentObjects
    }

    private fun getTheme(themeType: ThemeConf.ThemeType): Theme {
        return when (themeType) {
            ThemeConf.ThemeType.THEME1, ThemeConf.ThemeType.DEFAULT_THEME -> Theme1()
            ThemeConf.ThemeType.THEME2 -> Theme2()
            ThemeConf.ThemeType.CUSTOM_THEME -> customTheme ?: Theme1()
            else -> {
                Theme1()
            }
        }
    }
}

