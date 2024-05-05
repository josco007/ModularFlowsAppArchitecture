package com.chihuasdevs.androidui.views.FlowViews.tabBarView

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chihuasdevs.androidui.appScreens.loginView.LoginView
import com.chihuasdevs.androidui.appScreens.loginView.LoginViewModel

import com.chihuasdevs.androidui.base.BaseView
import com.chihuasdevs.androidui.utils.ResourcesUtil
import com.chihuasdevs.kmmCoreKit.enum.ImageAsset
import com.chihuasdevs.kmmCoreKit.models.ui.tabbar.TabBarButtonModel


class TabBarView(private val viewModel: TabBarViewModel): BaseView()  {


    private var rememberedViewModel: MutableState<TabBarViewModel>? = null


    @Composable
    override fun ComposableView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }
        val tabBarButtons = rememberedViewModel?.value?.tabBarButtons?.value
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabBarButtons?.forEach { button ->
                TabBarButton(button)
            }
        }
    }

    @Composable
    fun TabBarButton(button: TabBarButtonModel) {
        button.icon?.let {
            ResourcesUtil.getResourceId( LocalContext.current,
                it.value)
        }?.let { painterResource(it) }?.let {
            Image(
                painter = it,
                contentDescription = "Home Icon",
                modifier = Modifier.size(48.dp)
            )
        }
    }


}


@Preview(showBackground = true)
@Composable
fun TabBarViewPreview() {

    val viewModel: TabBarViewModel = viewModel()
    var buttons = mutableListOf<TabBarButtonModel>()
    buttons.add(TabBarButtonModel("1", "home", ImageAsset.HOME, true))
    buttons.add(TabBarButtonModel("2", "account", ImageAsset.ACCOUNT, true))
    buttons.add(TabBarButtonModel("3", "favorite", ImageAsset.FAVORITES, true))
    viewModel.setTabBarButtons(null, buttons)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            TabBarView(viewModel).ComposableView()
        }

    }
}

