package com.example.myapplication
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    var showColumnOptions by remember { mutableStateOf(false) } // 選択肢の表示状態
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("設定") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // 戻るボタンの処理
                        Icon(Icons.Filled.ArrowBack, contentDescription = "戻る")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                )
            )
        }
    ) { innerPadding ->
        Column( // MainContentの呼び出しを削除
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("設定画面")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { showColumnOptions = true }) { // ボタンクリックで選択肢を表示
                Text("表示列数を変更")
            }

            // 選択肢を表示するDropdownMenu
            DropdownMenu(
                expanded = showColumnOptions,
                onDismissRequest = { showColumnOptions = false }
            ) {
                DropdownMenuItem(
                    text = { Text("2列表示") },
                    onClick = {
                        // 2列表示にする処理
                        showColumnOptions = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("3列表示") },
                    onClick = {
                        // 3列表示にする処理
                        showColumnOptions = false
                    }
                )
            }
        }
    }
}