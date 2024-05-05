package com.chihuasdevs.androidui.appScreens.userInfoView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessUserInfoDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.UserInfoUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.models.ui.userInfo.UserInfo


class UserInfoViewModel(private val userInfoUIEventsDelegate: UserInfoUIEventsDelegate? = null) : ViewModel(),
    BusinessUserInfoDelegate {

    var name by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var isEmailValid by mutableStateOf(true)
    var isFieldsComplete by mutableStateOf(true)

    fun onNextTapped() {
        if (isEmailValid && isFieldsComplete) {
            userInfoUIEventsDelegate?.onNextTapped(null, UserInfo(name, lastName, email))
        }
    }

    fun validateEmailFormat(email: String) {
        isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateFields() {
        isFieldsComplete = name.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()
    }
}