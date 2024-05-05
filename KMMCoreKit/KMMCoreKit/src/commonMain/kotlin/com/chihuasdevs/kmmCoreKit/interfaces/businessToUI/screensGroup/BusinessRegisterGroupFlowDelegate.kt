package com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screensGroup

import com.chihuasdevs.kmmCoreKit.enum.UIAnimation

interface BusinessRegisterGroupFlowDelegate {

    fun openUserPassword(animation: UIAnimation?)
    fun openVerificationCode(animation: UIAnimation?)

}