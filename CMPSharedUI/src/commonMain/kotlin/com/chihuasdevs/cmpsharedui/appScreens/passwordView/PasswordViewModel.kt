package com.chihuasdevs.cmpsharedui.appScreens.passwordView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessPasswordDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.PasswordUIEventsDelegate

class PasswordViewModel(private val passwordUIEventsDelegate: PasswordUIEventsDelegate? = null) :
    BusinessPasswordDelegate {

    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var isPasswordValid by mutableStateOf(true)
    var arePasswordsMatching by mutableStateOf(true)

    fun onNextTapped() {
        if (isPasswordValid && arePasswordsMatching) {
            passwordUIEventsDelegate?.onNextTapped(null, password)
        }
    }

    fun validatePasswords() {
        isPasswordValid = password.length >= 6
        arePasswordsMatching = password == confirmPassword
    }
}