package com.chihuasdevs.kmmCoreKit.models.ui.tabbar

import com.chihuasdevs.kmmCoreKit.enum.ImageAsset

data class TabBarButtonModel(
    var id: String? = null,
    val name: String? = null,
    val icon: ImageAsset? = null,
    var isVisible: Boolean? = true
)