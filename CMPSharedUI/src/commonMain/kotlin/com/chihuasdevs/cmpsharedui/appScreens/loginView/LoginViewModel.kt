package com.chihuasdevs.cmpsharedui.appScreens.loginView

import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessLoginDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.LoginUIEventsDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginViewModel(
    var loginUIEventsDelegate: LoginUIEventsDelegate? = null
) : BusinessLoginDelegate {

    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    var username by mutableStateOf("")
    var password by mutableStateOf("")

    fun onLoginBtnTapped() {
        viewModelScope.launch {
            loginUIEventsDelegate?.onLogin(username, password)
        }
    }

    fun onLoginAppear() {
        loginUIEventsDelegate?.onAppear()
    }

    fun clear() {
        job.cancel()
    }
}
