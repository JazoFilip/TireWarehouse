package com.example.tirewarehouse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.tirewarehouse.ui.navigation.AppNavigation
import com.example.tirewarehouse.viewModel.TireViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlin.getValue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<TireViewModel>()
        val auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            auth.signInAnonymously()
                .addOnSuccessListener {
                    Log.d("AUTH", "Signed in anonymously")
                    viewModel.start()
                }
                .addOnFailureListener {
                    Log.e("AUTH", "Auth failed", it)
                }
        } else {
            viewModel.start()
        }

        setContent {
            AppNavigation(viewModel)
        }
    }
}
