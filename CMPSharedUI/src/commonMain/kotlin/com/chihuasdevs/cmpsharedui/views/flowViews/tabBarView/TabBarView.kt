package com.chihuasdevs.cmpsharedui.views.flowViews.tabBarView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chihuasdevs.cmpsharedui.base.BaseView
import com.chihuasdevs.cmpsharedui.utils.ResourcesUtil
import com.chihuasdevs.kmmCoreKit.models.ui.tabbar.TabBarButtonModel

class TabBarView(private val viewModel: TabBarViewModel) : BaseView() {

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
        val painter = ResourcesUtil.loadPainter(button.icon?.name)

        painter?.let {
            Image(
                painter = it,
                contentDescription = button.name ?: "",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}


/*
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

*/