package com.chihuasdevs.kmmbusinesskit.flows.registerFlow

import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo.OtpWebservicesDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessAppViewDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessPasswordDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessUserInfoDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessVerificationCodeDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screensGroup.BusinessRegisterGroupFlowDelegate
import com.chihuasdevs.kmmCoreKit.models.webServices.userWS.registerModels.request.RegisterUserRequest
import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit

class RegisterBusinessFlow: BusinessBaseFlow() {


    var registerUserRequest: RegisterUserRequest? = null
    var businessKit: KMMBusinessKit? = null
    var businessAppViewDelegate: BusinessAppViewDelegate? = null
    var businessRegisterGroupFlowDelegate: BusinessRegisterGroupFlowDelegate? = null
    var otpWebservicesDelegate: OtpWebservicesDelegate? = null

    private var businessUserInfoDelegate: BusinessUserInfoDelegate? = null
    private var businessVerificationCodeDelegate: BusinessVerificationCodeDelegate? = null
    private var businessPasswordDelegate: BusinessPasswordDelegate? = null

    fun start(businessKit: KMMBusinessKit?,
              businessAppViewDelegate: BusinessAppViewDelegate?,
              businessRegisterGroupFlowDelegate: BusinessRegisterGroupFlowDelegate?,
              businessUserInfoDelegate: BusinessUserInfoDelegate?,
              businessPasswordDelegate: BusinessPasswordDelegate?,
              businessVerificationCodeDelegate: BusinessVerificationCodeDelegate?,
              otpWebservicesDelegate: OtpWebservicesDelegate?){

        this.businessKit = businessKit
        this.businessAppViewDelegate = businessAppViewDelegate
        this.businessRegisterGroupFlowDelegate = businessRegisterGroupFlowDelegate
        this.businessUserInfoDelegate = businessUserInfoDelegate
        this.businessVerificationCodeDelegate = businessVerificationCodeDelegate
        this.businessPasswordDelegate = businessPasswordDelegate

        this.otpWebservicesDelegate = otpWebservicesDelegate

        this.registerUserRequest = RegisterUserRequest()
    }

}
