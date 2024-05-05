package com.chihuasdevs.androidui.appScreens.userInfoView

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

class UserInfoView(private val viewModel: UserInfoViewModel): BaseView() {

    var rememberedViewModel: MutableState<UserInfoViewModel>? = null

    @Composable
    override fun ComposableView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }

        Surface(modifier = Modifier.fillMaxSize()) {

            rememberedViewModel?.value?.validateFields()
            rememberedViewModel?.value?.validateEmailFormat(rememberedViewModel?.value?.email ?: "")

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                rememberedViewModel?.value?.name?.let { it ->
                    OutlinedTextField(
                        value = it,
                        onValueChange = { rememberedViewModel?.value?.name = it },
                        label = { Text("Nombre de usuario") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (rememberedViewModel?.value?.name.isNullOrEmpty()) {
                    Text("Campo obligatorio", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = rememberedViewModel?.value?.lastName ?: "",
                    onValueChange = { rememberedViewModel?.value?.lastName = it },
                    label = { Text("Apellido de usuario") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (rememberedViewModel?.value?.lastName.isNullOrEmpty()) {
                    Text("Campo obligatorio", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = rememberedViewModel?.value?.email ?: "",
                    onValueChange = { rememberedViewModel?.value?.email = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (rememberedViewModel?.value?.email.isNullOrEmpty()) {
                    Text("Campo obligatorio", color = Color.Red)
                }
                if (rememberedViewModel?.value?.isEmailValid == false) {
                    Text("Formato de correo electrónico no válido", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(16.dp))

                AppButtonView(AppButtonViewModel("Next", action = {
                    rememberedViewModel?.value?.onNextTapped()
                }), AndroidUI.getUIEnvObjects().theme.button1Theme).ComposableView()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoViewPreview() {
    val viewModel: UserInfoViewModel = viewModel()
    UserInfoView(viewModel).ComposableView()
}
