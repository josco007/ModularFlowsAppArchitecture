package com.chihuasdevs.androidui.appScreens.loginView

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chihuasdevs.androidui.base.BaseView
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner

class LoginView(private val viewModel: LoginViewModel): BaseView()  {


    private var rememberedViewModel: MutableState<LoginViewModel>? = null


    @Composable
    override fun ComposableView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }

        AndroidBackButtonHandler()

        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Iniciar sesión", style = MaterialTheme.typography.h4)

                Spacer(modifier = Modifier.height(16.dp))

                rememberedViewModel?.value?.username?.let { it ->
                    OutlinedTextField(
                        value = it,
                        onValueChange = { rememberedViewModel?.value?.username = it },
                        label = { Text("Nombre de usuario") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                rememberedViewModel?.value?.password?.let { it ->
                    OutlinedTextField(
                        value = it,
                        onValueChange = { rememberedViewModel?.value?.password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    rememberedViewModel?.value?.onLoginBtnTapped()
                }) {
                    Text("Login")
                }


                Button(onClick = {
                    rememberedViewModel?.value?.loginUIEventsDelegate?.onRegisterBtnTapped()
                }) {
                    Text("Register")
                }
            }
        }
    }

    @Composable
    fun AndroidBackButtonHandler() {
        val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current

        DisposableEffect(onBackPressedDispatcher) {
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.i("","")
                }
            }
            onBackPressedDispatcher?.onBackPressedDispatcher?.addCallback(callback)
            onDispose {
                callback.remove()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    val viewModel: LoginViewModel = viewModel()
    LoginView(viewModel).ComposableView()
}
