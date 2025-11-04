package com.chihuasdevs.cmpsharedui.appScreens.homeView

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chihuasdevs.cmpsharedui.CMPSharedUI
import com.chihuasdevs.cmpsharedui.base.BaseView
import com.chihuasdevs.cmpsharedui.views.commonViews.appButtonView.AppButtonView
import com.chihuasdevs.cmpsharedui.views.commonViews.appButtonView.AppButtonViewModel
import com.chihuasdevs.cmpsharedui.views.commonViews.navigationBarView.NavigationBarView
import com.chihuasdevs.cmpsharedui.views.commonViews.navigationBarView.NavigationBarViewModel
import com.chihuasdevs.cmpsharedui.views.flowViews.tabBarView.TabBarView
import com.chihuasdevs.kmmCoreKit.callbacks.NavigationBarActions

class HomeView(
    private val viewModel: HomeViewModel,
    private val tabBarView: TabBarView?
) : BaseView() {

    private var rememberedViewModel: MutableState<HomeViewModel>? = null
    private var rememberedTabBarView: MutableState<TabBarView>? = null

    @Composable
    override fun ComposableView() {
        rememberedViewModel = remember { mutableStateOf(viewModel) }
        rememberedTabBarView = remember { tabBarView?.let { mutableStateOf(it) } }

        // ⚠️ AndroidBackButtonHandler eliminado

        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                // NavigationBarView
                NavigationBarView(
                    NavigationBarViewModel(
                        "Home",
                        navigationBarActions = {
                            NavigationBarActions(onBack = {
                                rememberedViewModel?.value?.homeUIEventsDelegate?.onBackTapped()
                            })
                        })
                ).ComposableView()

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "¡Bienvenido a la página de inicio compose multiplatform!", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                AppButtonView(
                    AppButtonViewModel("Usar Theme 1", action = {
                        CMPSharedUI.changeTheme(CMPSharedUI.ThemeConf(CMPSharedUI.ThemeConf.ThemeType.THEME1))
                    }),
                    CMPSharedUI.getUIEnvObjects().theme.button1Theme
                ).ComposableView()

                AppButtonView(
                    AppButtonViewModel("Usar Theme 2", action = {
                        CMPSharedUI.changeTheme(CMPSharedUI.ThemeConf(CMPSharedUI.ThemeConf.ThemeType.THEME2))
                    }),
                    CMPSharedUI.getUIEnvObjects().theme.button2Theme
                ).ComposableView()

                AppButtonView(
                    AppButtonViewModel("Back", action = {
                        rememberedViewModel?.value?.homeUIEventsDelegate?.onBackTapped()
                    }),
                    style = CMPSharedUI.getUIEnvObjects().theme.button1Theme
                ).ComposableView()

                AppButtonView(
                    AppButtonViewModel("Space x rocket launches", action = {
                        rememberedViewModel?.value?.homeUIEventsDelegate?.onSpaceXRocketLaunchesTapped()
                    }),
                    CMPSharedUI.getUIEnvObjects().theme.button2Theme
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
}
