package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

//このファイルはメイン画面のUIとナビゲーションを定義するファイルです。
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHeader(navController: NavController) {
    TopAppBar(
        title = { Text("冷蔵庫のメモ") },
        navigationIcon = {
            IconButton(onClick = { /* ホームボタンの処理 */ }) {
                Icon(Icons.Rounded.Home, contentDescription = "ホーム")
            }
        },
        actions = {
            IconButton(onClick = { /* 時計ボタンの処理 */ }) {
                Icon(Icons.Filled.Schedule, contentDescription = "時計")
            }
            IconButton(onClick = { /* アップロードボタンの処理 */ }) {
                Icon(Icons.Filled.Upload, contentDescription = "アップロード")
            }
            IconButton(onClick = {
                navController.navigate("settings")
            }) {
                Icon(Icons.Filled.Settings, contentDescription = "設定")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    )
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 2x2のグリッドレイアウトを作成
        Row {
            MemoCard(isChecked = true, label = "Label", description = "Description", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            MemoCard(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            MemoCard(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            MemoCard(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // メモ入力欄
        TextField(
            value = "",
            onValueChange = { /* メモ入力処理 */ },
            label = { Text("メモ") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 鉛筆ボタン
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(onClick = { /* 鉛筆ボタンの処理（ポップアップ表示） */ }) {
                Icon(Icons.Filled.Edit, contentDescription = "編集")
            }
        }
    }
}

@Composable
fun MemoCard(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    label: String = "",
    description: String = ""
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (isChecked) {
                Checkbox(checked = isChecked, onCheckedChange = { /* チェックボックスの処理 */ })
            }
            Text(label)
            Text(description)
        }
    }
}
@Composable
fun MyScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeContent(navController) // HomeScreenからHomeContentに名前を変更
        }
        composable("settings") { SettingsScreen(navController) }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    Scaffold(
        topBar = { MyHeader(navController) }
    ) { innerPadding ->
        MainContent(Modifier.padding(innerPadding))
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyHeaderPreview() {
    MyHeader(navController = rememberNavController())
}

// MainContentのプレビュー
@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    MainContent()
}

// MemoCardのプレビュー
@Preview(showBackground = true)
@Composable
fun MemoCardPreview() {
    MemoCard(isChecked = true, label = "Label", description = "Description")
}

// 全てのComposable関数をプレビューするための関数
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MyScreen()
    }
}

