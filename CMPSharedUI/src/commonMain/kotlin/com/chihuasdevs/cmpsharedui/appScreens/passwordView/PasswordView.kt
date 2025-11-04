package com.chihuasdevs.cmpsharedui.appScreens.passwordView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chihuasdevs.cmpsharedui.CMPSharedUI
import com.chihuasdevs.cmpsharedui.base.BaseView
import com.chihuasdevs.cmpsharedui.views.commonViews.appButtonView.AppButtonView
import com.chihuasdevs.cmpsharedui.views.commonViews.appButtonView.AppButtonViewModel

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
                }), style = CMPSharedUI.getUIEnvObjects().theme.button1Theme).ComposableView()
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PasswordViewPreview() {
    val viewModel: PasswordViewModel = viewModel()
    PasswordView(viewModel).ComposableView()
}


 */
