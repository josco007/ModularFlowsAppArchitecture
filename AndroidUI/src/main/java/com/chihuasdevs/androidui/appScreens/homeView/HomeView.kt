package com.chihuasdevs.androidui.appScreens.homeView

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chihuasdevs.androidui.AndroidUI
import com.chihuasdevs.androidui.base.BaseView
import com.chihuasdevs.androidui.themes.Theme1
import com.chihuasdevs.androidui.views.FlowViews.tabBarView.TabBarView
import com.chihuasdevs.androidui.views.commonViews.appButtonView.AppButtonView
import com.chihuasdevs.androidui.views.commonViews.appButtonView.AppButtonViewModel
import com.chihuasdevs.androidui.views.commonViews.navigationBarView.NavigationBarView
import com.chihuasdevs.androidui.views.commonViews.navigationBarView.NavigationBarViewModel
import com.chihuasdevs.kmmCoreKit.callbacks.NavigationBarActions

class HomeView(private val viewModel: HomeViewModel, private val tabBarView: TabBarView?): BaseView() {

    private var rememberedViewModel: MutableState<HomeViewModel>? = null
    private var rememberedTabBarView: MutableState<TabBarView>? = null

    @Composable
    override fun ComposableView() {
        rememberedViewModel = remember { mutableStateOf(viewModel) }
        rememberedTabBarView = remember { tabBarView?.let { mutableStateOf(it) } }

        AndroidBackButtonHandler()

        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                NavigationBarView(NavigationBarViewModel("Home",
                    navigationBarActions = {
                        NavigationBarActions(onBack = {
                            rememberedViewModel?.value?.homeUIEventsDelegate?.onBackTapped()
                        })
                    })).ComposableView() // Agrega la NavigationBarView en la parte superior

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "¡Bienvenido a la página de inicio!", style = MaterialTheme.typography.h4)

                Spacer(modifier = Modifier.height(16.dp))

                AppButtonView(
                    AppButtonViewModel("Usar Theme 1", action = {
                        AndroidUI.changeTheme(AndroidUI.ThemeConf(AndroidUI.ThemeConf.ThemeType.THEME1))
                    }),
                    AndroidUI.getUIEnvObjects().theme.button1Theme
                ).ComposableView()

                AppButtonView(
                    AppButtonViewModel("Usar Theme 2", action = {
                        AndroidUI.changeTheme(AndroidUI.ThemeConf(AndroidUI.ThemeConf.ThemeType.THEME2))
                    }),
                    AndroidUI.getUIEnvObjects().theme.button2Theme
                ).ComposableView()

                Text(text = "ejemplo de boton 1 ")
                AppButtonView(
                    AppButtonViewModel("Back", action = {
                        rememberedViewModel?.value?.homeUIEventsDelegate?.onBackTapped()
                    }),
                    style = AndroidUI.getUIEnvObjects().theme.button1Theme
                ).ComposableView()

                Text(text = "ejemplo de boton 2 ")

                AppButtonView(
                    AppButtonViewModel("Space x rocket launches", action = {
                        rememberedViewModel?.value?.homeUIEventsDelegate?.onSpaceXRocketLaunchesTapped()
                    }),
                    AndroidUI.getUIEnvObjects().theme.button2Theme
                ).ComposableView()

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    rememberedTabBarView?.value?.ComposableView()
                }
            }
        }
    }

    @Composable
    fun AndroidBackButtonHandler() {
        val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current

        DisposableEffect(onBackPressedDispatcher) {
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    rememberedViewModel?.value?.homeUIEventsDelegate?.onBackTapped()
                }
            }
            onBackPressedDispatcher?.onBackPressedDispatcher?.addCallback(callback)
            onDispose {
                callback.remove()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    AndroidUI.initUIKit(AndroidUI.ThemeConf(AndroidUI.ThemeConf.ThemeType.THEME1))
    val viewModel: HomeViewModel = viewModel()
    HomeView(viewModel, null).ComposableView()
}
