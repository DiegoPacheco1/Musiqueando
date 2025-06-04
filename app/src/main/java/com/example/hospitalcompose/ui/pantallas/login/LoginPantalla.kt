// LoginPantalla.kt
package com.example.hospitalcompose.ui.pantallas.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.R
import com.example.hospitalcompose.common.Constantes
import com.example.hospitalcompose.ui.utils.common.UiEvent

@Composable
fun LoginPantalla(
    viewModel: LoginViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    onNavigateToListaArtistas: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent?.let {
            when (it) {
                is UiEvent.ShowSnackbar -> showSnackbar(it.message)
                is UiEvent.Navigate     -> onNavigateToListaArtistas()
            }
            viewModel.handleEvent(LoginContract.LoginEvent.UiEventDone)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo ligeramente reducido y centrado
        Image(
            painter = painterResource(id = R.drawable.jema_lh_058_19),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(1f)
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )

        // Formulario sobre el fondo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Aquí reemplaza el Box por este Row para título + icono al lado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Musiqueando",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.notablanca),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }

            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = {
                    viewModel.handleEvent(LoginContract.LoginEvent.UpdateUsername(it))
                },
                label = { Text(Constantes.LABEL_USUARIO) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
                    viewModel.handleEvent(LoginContract.LoginEvent.UpdatePassword(it))
                },
                label = { Text(Constantes.LABEL_CONTRASENA) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Button(
                onClick = {
                    if (uiState.isLoginMode) {
                        viewModel.handleEvent(
                            LoginContract.LoginEvent.Login(
                                uiState.nombre,
                                uiState.password
                            )
                        )
                    } else {
                        viewModel.handleEvent(
                            LoginContract.LoginEvent.Register(
                                uiState.nombre,
                                uiState.password
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (uiState.isLoginMode)
                        Constantes.BOTON_INICIAR_SESION
                    else
                        Constantes.BOTON_REGISTRARSE
                )
            }

            Text(
                text = if (uiState.isLoginMode)
                    "¿No tienes cuenta? Regístrate"
                else
                    "¿Ya tienes cuenta? Inicia sesión",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable {
                        viewModel.handleEvent(
                            LoginContract.LoginEvent.UpdateLoginMode(
                                !uiState.isLoginMode
                            )
                        )
                    }
            )
        }
    }
}
