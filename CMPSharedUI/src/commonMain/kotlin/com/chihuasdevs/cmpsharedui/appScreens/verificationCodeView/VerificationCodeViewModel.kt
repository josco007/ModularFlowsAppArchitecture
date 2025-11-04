package com.chihuasdevs.cmpsharedui.appScreens.verificationCodeView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessVerificationCodeDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.VerificationCodeUIEventsDelegate

class VerificationCodeViewModel(private val verificationCodeUIEventsDelegate: VerificationCodeUIEventsDelegate? = null) :
    BusinessVerificationCodeDelegate {

    var verificationCode by mutableStateOf("")


    fun onNextTapped() {
        verificationCodeUIEventsDelegate?.onNextTapped(null, verificationCode)
    }


}