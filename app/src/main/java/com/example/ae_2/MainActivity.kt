package com.example.ae_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ae_2.ui.theme.AE2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AE2Theme {
                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            NavigationBar {
                                NavigationBarItem(
                                    icon = { Icon(Icons.Rounded.Home, contentDescription = "Home") },
                                    label = { Text("Home") },
                                    selected = false,
                                    onClick = { /* Handle click */ }
                                )
                                NavigationBarItem(
                                    icon = { Icon(Icons.Rounded.Info, contentDescription = "Explorar") },
                                    label = { Text("Explorar") },
                                    selected = true,
                                    onClick = { /* Handle click */ }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize())
                {
                    innerPadding ->
                    SeriesScreen(modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

