package com.chihuasdevs.cmpsharedui.themes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chihuasdevs.cmpsharedui.views.commonViews.appButtonView.AppButtonView

class Theme2 : Theme() {

    override fun setButton1Theme() {
        val loadingStyle = AppButtonView.AppButtonViewStyle.LoadingStyle(
            position = AppButtonView.AppButtonViewStyle.LoadingPosition.LEADING,
            spinnerColor = Color.White,
            width = 30.dp,
            height = 30.dp,
        )
        val textStyle = AppButtonView.AppButtonViewStyle.TextStyle(
            alignment = TextAlign.Left,
            textColor = Color.White
        )
        button1Theme = AppButtonView.AppButtonViewStyle(
            cornerRadius = 90f,
            padding = 16.dp,
            background = Color.Black,
            loadingStyle = loadingStyle,
            textStyle = textStyle
        )
    }

    override fun setButton2Theme() {
        button2Theme = AppButtonView.AppButtonViewStyle(cornerRadius = 90f)
    }

}