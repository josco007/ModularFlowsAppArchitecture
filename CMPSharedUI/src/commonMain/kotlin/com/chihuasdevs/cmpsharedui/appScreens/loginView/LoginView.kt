package com.chihuasdevs.cmpsharedui.appScreens.loginView

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chihuasdevs.cmpsharedui.base.BaseView

class LoginView(private val viewModel: LoginViewModel) : BaseView() {

    private var rememberedViewModel: MutableState<LoginViewModel>? = null

    @Composable
    override fun ComposableView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }

        // ⚠️ Android BackButtonHandler eliminado en multiplataforma

        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Iniciar sesión", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                rememberedViewModel?.value?.username?.let { username ->
                    OutlinedTextField(
                        value = username,
                        onValueChange = { rememberedViewModel?.value?.username = it },
                        label = { Text("Nombre de usuario") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                rememberedViewModel?.value?.password?.let { password ->
                    OutlinedTextField(
                        value = password,
                        onValueChange = { rememberedViewModel?.value?.password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { rememberedViewModel?.value?.onLoginBtnTapped() }) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    rememberedViewModel?.value?.loginUIEventsDelegate?.onRegisterBtnTapped()
                }) {
                    Text("Register")
                }
            }
        }
    }
}
