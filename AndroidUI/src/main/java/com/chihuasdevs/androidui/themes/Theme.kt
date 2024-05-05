package com.chihuasdevs.androidui.themes

import com.chihuasdevs.androidui.views.commonViews.appButtonView.AppButtonView

open class Theme {

    var button1Theme: AppButtonView.AppButtonViewStyle = AppButtonView.AppButtonViewStyle()
    var button2Theme: AppButtonView.AppButtonViewStyle = AppButtonView.AppButtonViewStyle()

    init {
        setButton1Theme()
        setButton2Theme()
    }

    open fun setButton1Theme() {}

    open fun setButton2Theme() {}
}