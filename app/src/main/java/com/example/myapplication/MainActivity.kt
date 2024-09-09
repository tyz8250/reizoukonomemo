package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeContent(navController) }
                composable("settings") { SettingsScreen(navController) }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyHeaderPreview() {
    MyHeader(navController = rememberNavController())
}

// 全てのComposable関数をプレビューするための関数
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        HomeContent(navController) // navController を渡す
    }
}}
