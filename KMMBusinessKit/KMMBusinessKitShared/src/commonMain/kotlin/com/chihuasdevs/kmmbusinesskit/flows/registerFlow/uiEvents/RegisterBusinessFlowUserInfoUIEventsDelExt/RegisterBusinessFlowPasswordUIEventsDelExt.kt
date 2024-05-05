package com.chihuasdevs.kmmbusinesskit.flows.registerFlow.uiEvents.RegisterBusinessFlowPasswordUIEventsDelExt

import com.chihuasdevs.kmmCoreKit.base.CoreKitBaseViewModel
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.PasswordUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.models.webServices.otpWS.generateOtp.request.GenerateOtpCodeRequest
import com.chihuasdevs.kmmbusinesskit.flows.registerFlow.RegisterBusinessFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val RegisterBusinessFlow.passwordUIEventsDelegate: PasswordUIEventsDelegate
    get() {
        val registerBusinessFlowInstance = this

        return object : PasswordUIEventsDelegate {
            override fun onNextTapped(userDataViewModel: CoreKitBaseViewModel?, password: String) {
                registerBusinessFlowInstance.registerUserRequest?.apply {
                    this.password = password
                }

                val loadingId = businessAppViewDelegate?.showLoading(registerBusinessFlowInstance, true, null)
                MainScope().launch {
                    val otpResult = otpWebservicesDelegate?.generateOtpCode(otpCodeRequest = GenerateOtpCodeRequest())

                    withContext(Dispatchers.Main) {
                        businessRegisterGroupFlowDelegate?.openVerificationCode(UIAnimation.FORWARD)
                        businessAppViewDelegate?.showLoading(registerBusinessFlowInstance, false, loadingId)
                    }
                }
            }
        }
    }
