package com.chihuasdevs.androidui.appScreens.appView

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration


class AppView(private val viewModel: AppViewModel) {



    var rememberedViewModel: MutableState<AppViewModel>? = null


    @Composable
    fun AppView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }

        rememberedViewModel?.value?.screenWith = LocalConfiguration.current.screenWidthDp.dp.value

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            rememberedViewModel?.let {
                MainViewContent(viewModel = it)
            }


        }
    }



    @Composable
    fun MainViewContent( viewModel: MutableState<AppViewModel>) {

        val transition = updateTransition(0.dp, label = "offsetTransaction")
        // Mostrar solo la vista actual si está visible

        Box(Modifier.fillMaxSize()) {
            if (viewModel.value.isCurrentViewVisible.value) {

                val currentViewXOffsetAnimated by transition.animateDp(label = "") {
                    viewModel.value.currentViewXOffset.value.dp
                }

                Box(
                    Modifier.offset(
                        if (!viewModel.value.currentViewAnimateWhenXOffsetChange) viewModel.value.currentViewXOffset.value.dp else currentViewXOffsetAnimated,
                        0.dp
                    )
                ) {
                    viewModel.value.currentView?.invoke()
                }
            }

            if (viewModel.value.isOldViewVisible.value) {

                val oldViewXOffsetAnimated by transition.animateDp(label = "") {
                    viewModel.value.oldViewXOffset.value.dp
                }

                Box(Modifier.fillMaxSize()) {

                    Box(
                        Modifier.offset(
                            if (!viewModel.value.oldViewAnimateWhenXOffsetChange) viewModel.value.oldViewXOffset.value.dp else oldViewXOffsetAnimated,
                            0.dp
                        )
                    ) {
                        viewModel.value.oldView?.invoke()
                    }
                }
            }

            if (viewModel.value.currentOverlayView?.view != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    viewModel.value.currentOverlayView?.view?.invoke()
                }
            }

        }
    }
}
