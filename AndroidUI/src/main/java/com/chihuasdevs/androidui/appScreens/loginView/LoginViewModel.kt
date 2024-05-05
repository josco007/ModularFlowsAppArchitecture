package com.chihuasdevs.androidui.appScreens.loginView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessLoginDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.LoginUIEventsDelegate
import kotlinx.coroutines.launch

class LoginViewModel(): ViewModel(), BusinessLoginDelegate {


    var loginUIEventsDelegate: LoginUIEventsDelegate? = null

    var username by mutableStateOf("")
    var password by mutableStateOf("")



    constructor(loginUIEventsDelegate: LoginUIEventsDelegate? = null) : this() {
        this.loginUIEventsDelegate = loginUIEventsDelegate
    }

    fun onLoginBtnTapped(){
        viewModelScope.launch {
            loginUIEventsDelegate?.onLogin(username, password)
        }
    }

    fun onLoginAppear() {
        loginUIEventsDelegate?.onAppear()
    }

}