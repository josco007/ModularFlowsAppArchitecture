package com.chihuasdevs.androidui.themes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chihuasdevs.androidui.views.commonViews.appButtonView.AppButtonView
import androidx.compose.ui.text.style.TextAlign

class Theme1 : Theme() {

    override fun setButton1Theme() {
        val loadingStyle = AppButtonView.AppButtonViewStyle.LoadingStyle(
            position = AppButtonView.AppButtonViewStyle.LoadingPosition.LEADING,
            spinnerColor = Color.Blue,
            width = 30.dp,
            height = 30.dp,
        )
        val textStyle = AppButtonView.AppButtonViewStyle.TextStyle(
            alignment = TextAlign.Left,
            textColor = Color.Gray
        )
        button1Theme = AppButtonView.AppButtonViewStyle(
            cornerRadius = 0f,
            padding = 16.dp,
            background = Color.Yellow,
            loadingStyle = loadingStyle,
            textStyle = textStyle
        )
    }

    override fun setButton2Theme() {
        button2Theme = AppButtonView.AppButtonViewStyle(cornerRadius = 0f, background = Color.Green)
    }

}