package com.chihuasdevs.kmmbusinesskit.flows.registerFlow.uiEvents.RegisterBusinessFlowUserInfoUIEventsDelExt

import com.chihuasdevs.kmmCoreKit.base.CoreKitBaseViewModel
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.VerificationCodeUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.models.user.webServices.otpModels.request.ValidateOtpCodeRequest
import com.chihuasdevs.kmmbusinesskit.flows.registerFlow.RegisterBusinessFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val RegisterBusinessFlow.verificationCodeUIEventsDelegate: VerificationCodeUIEventsDelegate
    get() {
        val registerBusinessFlowInstance = this

        return object : VerificationCodeUIEventsDelegate {
            override fun onNextTapped(userDataViewModel: CoreKitBaseViewModel?, code: String) {

                val loadingId = businessAppViewDelegate?.showLoading(registerBusinessFlowInstance, true, null)
                MainScope().launch {
                    val otpResult = otpWebservicesDelegate?.validateOtpCode(ValidateOtpCodeRequest(code))
                    if (otpResult != null && otpResult.isSuccess) {
                        withContext(Dispatchers.Main) {
                            businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.HOME, FlowManagerBase.FlowAction.START, UIAnimation.FORWARD)
                            businessAppViewDelegate?.showLoading(registerBusinessFlowInstance, false, loadingId)
                            businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.REGISTER, FlowManagerBase.FlowAction.DESTROY, null)
                        }
                    }
                    else {
                        //TODO: handle error
                    }

                }
            }
        }
    }
