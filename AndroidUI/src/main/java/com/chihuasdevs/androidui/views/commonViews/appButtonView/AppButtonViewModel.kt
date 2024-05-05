package com.chihuasdevs.androidui.views.commonViews.appButtonView

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AppButtonViewModel(
    var title: String,
    val action: () -> Unit
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

    fun performAction() {
        _isLoading.value = true
        action()
    }

    fun setIsLoading(isLoading: Boolean){
        _isLoading.value = isLoading
    }
}