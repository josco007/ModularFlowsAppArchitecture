package com.chihuasdevs.cmpsharedui.appScreens.verificationCodeView

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


class VerificationCodeView(private val viewModel: VerificationCodeViewModel): BaseView() {

    var rememberedViewModel: MutableState<VerificationCodeViewModel>? = null

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
                    value = rememberedViewModel?.value?.verificationCode ?: "",
                    onValueChange = {
                        rememberedViewModel?.value?.verificationCode = it
                    },
                    label = { Text("Codigo otp") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (rememberedViewModel?.value?.verificationCode.isNullOrEmpty()) {
                    Text("Campo obligatorio", color = Color.Red)
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
fun VerificationCodeViewPreview() {
    val viewModel: VerificationCodeViewModel = viewModel()
    VerificationCodeView(viewModel).ComposableView()
}


 */
