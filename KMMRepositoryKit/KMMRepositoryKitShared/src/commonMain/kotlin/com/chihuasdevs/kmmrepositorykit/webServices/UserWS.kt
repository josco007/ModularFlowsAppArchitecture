package com.chihuasdevs.kmmrepositorykit.webServices

import com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo.UserWebservicesDelegate
import com.chihuasdevs.kmmCoreKit.models.webServices.userWS.loginModels.request.LoginRequest
import com.chihuasdevs.kmmCoreKit.models.webServices.userWS.loginModels.response.LoginResponse
import kotlinx.coroutines.delay


class UserWS: UserWebservicesDelegate {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {

            delay(5000)

            val token = "dummy_token_${loginRequest.username}_${loginRequest.password}"

            Result.success(LoginResponse(token))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}