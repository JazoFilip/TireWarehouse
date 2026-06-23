package com.example.tirewarehouse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tirewarehouse.navigation.AppNavigation
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (com.google.firebase.FirebaseApp.getApps(this).isEmpty()) {
            com.google.firebase.FirebaseApp.initializeApp(this)
        }
        val auth = FirebaseAuth.getInstance()
        setContent {
            var isUserAuthenticated by remember { mutableStateOf(auth.currentUser != null) }

            if (!isUserAuthenticated) {
                auth.signInAnonymously()
                    .addOnSuccessListener {
                        Log.d("AUTH", "Signed in anonymously")
                        isUserAuthenticated = true
                    }
                    .addOnFailureListener { exception ->
                        Log.e("AUTH", "Auth failed", exception)
                    }
            }

            if (isUserAuthenticated) {
                AppNavigation()
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}