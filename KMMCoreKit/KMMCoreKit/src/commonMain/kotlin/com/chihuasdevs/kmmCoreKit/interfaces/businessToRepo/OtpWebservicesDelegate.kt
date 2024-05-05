package com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo

import com.chihuasdevs.kmmCoreKit.models.webServices.otpWS.generateOtp.request.GenerateOtpCodeRequest
import com.chihuasdevs.kmmCoreKit.models.user.webServices.otpModels.request.ValidateOtpCodeRequest

import com.chihuasdevs.kmmCoreKit.models.user.webServices.otpModels.response.ValidateOtpCodeResponse

interface OtpWebservicesDelegate {
    suspend fun generateOtpCode(otpCodeRequest: GenerateOtpCodeRequest): Result<ValidateOtpCodeResponse>
    suspend fun validateOtpCode(validateOtpCodeResponse: ValidateOtpCodeRequest): Result<ValidateOtpCodeResponse>

}