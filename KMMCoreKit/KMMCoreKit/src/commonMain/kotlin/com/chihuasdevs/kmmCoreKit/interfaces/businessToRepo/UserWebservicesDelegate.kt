package com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo

import com.chihuasdevs.kmmCoreKit.models.webServices.userWS.loginModels.request.LoginRequest
import com.chihuasdevs.kmmCoreKit.models.webServices.userWS.loginModels.response.LoginResponse

interface UserWebservicesDelegate {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
}