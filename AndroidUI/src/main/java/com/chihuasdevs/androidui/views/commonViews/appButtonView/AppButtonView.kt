package com.chihuasdevs.androidui.views.commonViews.appButtonView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chihuasdevs.androidui.base.BaseView

class AppButtonView(private val viewModel: AppButtonViewModel,
                    private val style: AppButtonViewStyle = AppButtonViewStyle()): BaseView() {

    var rememberedViewModel: MutableState<AppButtonViewModel>? = null

    data class AppButtonViewStyle(
        var cornerRadius: Float = 12f,
        var padding: Dp = 16.dp,
        var background: Color = Color.Blue,
        var loadingStyle: LoadingStyle = LoadingStyle(),
        var textStyle: TextStyle = TextStyle()
    ) {
        data class LoadingStyle(
            var position: LoadingPosition = LoadingPosition.TRAILING,
            var spinnerColor: Color = Color.Black,
            var width: Dp = 20.dp,
            var height: Dp = 20.dp
        )

        enum class LoadingPosition {
            LEADING, TRAILING
        }

        data class TextStyle(
            var alignment: TextAlign = TextAlign.Center,
            var textColor: Color = Color.White
        )
    }

    @Composable
    override fun ComposableView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }

        Box(
            modifier = Modifier
                .padding(style.padding)
                .background(
                    color = style.background,
                    shape = RoundedCornerShape(style.cornerRadius)
                )
                .clickable(enabled = !viewModel.isLoading.value) {
                    rememberedViewModel?.value?.setIsLoading(true)
                    rememberedViewModel?.value?.action?.let { it() }
                }
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (viewModel.isLoading.value && style.loadingStyle.position == AppButtonViewStyle.LoadingPosition.LEADING) {
                    LoadingView(style.loadingStyle)
                }

                Text(
                    text = viewModel.title,
                    style = MaterialTheme.typography.body1,
                    color = style.textStyle.textColor,
                    textAlign = style.textStyle.alignment,
                    modifier = Modifier.weight(1f)
                )

                if (viewModel.isLoading.value && style.loadingStyle.position == AppButtonViewStyle.LoadingPosition.TRAILING) {
                    LoadingView(style.loadingStyle)
                }
            }
        }
    }

    @Composable
    fun LoadingView(style: AppButtonViewStyle.LoadingStyle) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(style.width, style.height)
        ) {
            CircularProgressIndicator(
                color = style.spinnerColor,
                modifier = Modifier.alpha(0.8f)
            )
        }
    }
}