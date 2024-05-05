package com.chihuasdevs.androidui.appScreens.passwordView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chihuasdevs.androidui.AndroidUI

import com.chihuasdevs.androidui.base.BaseView
import com.chihuasdevs.androidui.views.commonViews.appButtonView.AppButtonView
import com.chihuasdevs.androidui.views.commonViews.appButtonView.AppButtonViewModel

class PasswordView(private val viewModel: PasswordViewModel): BaseView() {

    var rememberedViewModel: MutableState<PasswordViewModel>? = null

    @Composable
    override fun ComposableView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }

        Surface(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = rememberedViewModel?.value?.password ?: "",
                    onValueChange = {
                        rememberedViewModel?.value?.password = it
                        rememberedViewModel?.value?.validatePasswords() // Validar contraseñas
                    },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (rememberedViewModel?.value?.password.isNullOrEmpty()) {
                    Text("Campo obligatorio", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = rememberedViewModel?.value?.confirmPassword ?: "",
                    onValueChange = {
                        rememberedViewModel?.value?.confirmPassword = it
                        rememberedViewModel?.value?.validatePasswords() // Validar contraseñas
                    },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (rememberedViewModel?.value?.confirmPassword.isNullOrEmpty()) {
                    Text("Campo obligatorio", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))

                if (!rememberedViewModel?.value?.isPasswordValid!!) {
                    Text("La contraseña debe tener al menos 6 caracteres", color = Color.Red)
                }
                if (!rememberedViewModel?.value?.arePasswordsMatching!!) {
                    Text("Las contraseñas no coinciden", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))

                AppButtonView(AppButtonViewModel("Next", action = {
                    rememberedViewModel?.value?.onNextTapped()
                }), style = AndroidUI.getUIEnvObjects().theme.button1Theme).ComposableView()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordViewPreview() {
    val viewModel: PasswordViewModel = viewModel()
    PasswordView(viewModel).ComposableView()
}
