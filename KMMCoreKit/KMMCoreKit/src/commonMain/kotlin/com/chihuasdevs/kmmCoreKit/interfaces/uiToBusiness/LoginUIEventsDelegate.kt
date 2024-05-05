package com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness


interface LoginUIEventsDelegate {
    fun onAppear()
    suspend fun onLogin(user: String, password: String)
    fun onRegisterBtnTapped()
}