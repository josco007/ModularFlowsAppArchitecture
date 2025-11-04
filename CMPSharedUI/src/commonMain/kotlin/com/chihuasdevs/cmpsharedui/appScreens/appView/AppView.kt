package com.chihuasdevs.cmpsharedui.appScreens.appView

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class AppView(private val viewModel: AppViewModel) {

    var rememberedViewModel: MutableState<AppViewModel>? = null

    @Suppress("NotConstructor")
    @Composable
    fun AppView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }

        // Reemplazo de LocalConfiguration usando BoxWithConstraints
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val screenWidth: Dp = maxWidth
            rememberedViewModel?.value?.screenWith = screenWidth.value

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
    }

    @Composable
    fun MainViewContent(viewModel: MutableState<AppViewModel>) {

        val transition = updateTransition(0.dp, label = "offsetTransaction")

        Box(Modifier.fillMaxSize()) {

            if (viewModel.value.isCurrentViewVisible.value) {

                val currentViewXOffsetAnimated by transition.animateDp(label = "") {
                    viewModel.value.currentViewXOffset.value.dp
                }

                Box(
                    Modifier.offset(
                        if (!viewModel.value.currentViewAnimateWhenXOffsetChange)
                            viewModel.value.currentViewXOffset.value.dp
                        else currentViewXOffsetAnimated,
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
                            if (!viewModel.value.oldViewAnimateWhenXOffsetChange)
                                viewModel.value.oldViewXOffset.value.dp
                            else oldViewXOffsetAnimated,
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
