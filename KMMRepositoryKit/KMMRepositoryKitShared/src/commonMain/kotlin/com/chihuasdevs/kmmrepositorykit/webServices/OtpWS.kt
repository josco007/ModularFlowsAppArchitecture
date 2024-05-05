package com.chihuasdevs.kmmrepositorykit.webServices

import com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo.OtpWebservicesDelegate
import com.chihuasdevs.kmmCoreKit.models.webServices.otpWS.generateOtp.request.GenerateOtpCodeRequest
import com.chihuasdevs.kmmCoreKit.models.user.webServices.otpModels.request.ValidateOtpCodeRequest
import com.chihuasdevs.kmmCoreKit.models.user.webServices.otpModels.response.ValidateOtpCodeResponse
import kotlinx.coroutines.delay

class OtpWS: OtpWebservicesDelegate {
    override suspend fun generateOtpCode(otpCodeRequest: GenerateOtpCodeRequest): Result<ValidateOtpCodeResponse> {
        return try {

            delay(3000)

            Result.success(ValidateOtpCodeResponse())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun validateOtpCode(validateOtpCodeResponse: ValidateOtpCodeRequest): Result<ValidateOtpCodeResponse> {
        return try {

            delay(3000)

            Result.success(ValidateOtpCodeResponse())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}