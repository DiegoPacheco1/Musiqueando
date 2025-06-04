package com.example.hospitalcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hospitalcompose.theme.HospitalComposeTheme
import com.example.hospitalcompose.ui.utils.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HospitalComposeTheme {
                Navigation()
            }
        }
    }
}
