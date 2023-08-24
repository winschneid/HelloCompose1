package com.example.hellocompose1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hellocompose1.viewModel.SampleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = "screen1") {
//                composable(route = "screen1") {
//                    Screen1 { text, id ->
//                        navController.navigate("screen2/$text/$id")
//                    }
//                }
//                composable(
//                    route = "screen2/{text}/{id}",
//                    arguments = listOf(
//                        navArgument("text") { type = NavType.StringType },
//                        navArgument("id") { type = NavType.IntType }
//                    )
//                ) { backStackEntry ->
//                    val text = backStackEntry.arguments?.getString("text") ?: ""
//                    val id = backStackEntry.arguments?.getInt("id") ?: 0
//                    Screen2(text, id) { navController.navigateUp() }
//                }
//            }
        }
    }
}

@Composable
fun AppScreen() {
    Column {
        CountText()
        CountButton()
    }
}

@Composable
fun CountText(viewModel: SampleViewModel = viewModel()) {
    val count: Int by viewModel.count.observeAsState(0)
    Text(text = "Count: $count")
}

@Composable
fun CountButton(viewModel: SampleViewModel = viewModel()) {
    Button(onClick = { viewModel.countUp() }) {
        Text(text = "Count up")
    }
}

@Composable
fun Screen1(onClickButton: (String, Int)->Unit = { _, _ -> }) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Screen 1")
        Button(onClick = { onClickButton("Good morning!", 1) }) {
            Text(text = "(1) Good morning!")
        }
        Button(onClick = { onClickButton("Good afternoon!", 2) }) {
            Text(text = "(2) Good afternoon!")
        }
    }
}

@Composable
fun Screen2(text: String = "text", id: Int = 0, onClickButton: ()->Unit = {}) {
    Column {
        Text(text = "($id) $text")
        Button(onClick = onClickButton) {
            Text(text = "Back to Screen 1")
        }
    }
}