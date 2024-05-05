package com.chihuasdevs.androidui.views.commonViews.navigationBarView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.callbacks.NavigationBarActions
import com.chihuasdevs.kmmCoreKit.models.ui.tabbar.TabBarButtonModel


class NavigationBarViewModel(title: String,
                             navigationBarActions: (() -> NavigationBarActions)? = null): ViewModel() {

    var title: MutableState<String> = mutableStateOf(title)
    var navigationBarActions: MutableState<(() -> NavigationBarActions)? > = mutableStateOf(navigationBarActions)

}