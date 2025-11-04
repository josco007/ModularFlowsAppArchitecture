package com.chihuasdevs.cmpsharedui.appScreens.loginView


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    val viewModel = remember { LoginViewModel() }
    LoginView(viewModel)
}