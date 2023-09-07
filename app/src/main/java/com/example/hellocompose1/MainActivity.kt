package com.example.hellocompose1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.hellocompose1.viewModel.SampleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            AppScreen()
            List1()
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

@Composable
fun List1() {
    val fruits = listOf("Apple", "Orange", "Grape", "Peach", "Strawberry")
    LazyColumn {
        items(fruits) { fruit ->
            Text(text = "This is $fruit")
            Image(
                painter = rememberAsyncImagePainter("https://i0.wp.com/codingwithrashid.com/wp-content/uploads/2022/08/Android-jetpack-compose-image-url.png"),
                contentDescription = "image",
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )
        }
    }
}

@Preview
@Composable
fun ListPreview() {
    List1()
}


@Composable
@Preview
fun InversionLayoutExamplePreview_LTR_True() {
    InversionLayoutExample(LayoutDirection.Ltr, true)
}

@Composable
@Preview
fun InversionLayoutExamplePreview_LTR_False() {
    InversionLayoutExample(LayoutDirection.Ltr, false)
}

@Composable
@Preview
fun InversionLayoutExamplePreview_RTL_True() {
    InversionLayoutExample(LayoutDirection.Rtl, true)
}

@Composable
@Preview
fun InversionLayoutExamplePreview_RTL_False() {
    InversionLayoutExample(LayoutDirection.Rtl, false)
}

@Composable
fun InversionLayoutExample(
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    idStart: Boolean = true) {
    Column(modifier = Modifier.width(200.dp)) {
        ProvideLayoutDirection(layoutDirection = layoutDirection) {
            InversionLayout(idStart)
        }
    }
}

@Composable
private fun ProvideLayoutDirection(
    layoutDirection: LayoutDirection,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection,
        content = content,
    )
}

@Composable
@Preview
private fun InversionLayoutPreview() {
    InversionLayout()
}

@Composable
private fun InversionLayout(isStart: Boolean = true) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .width(200.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(200.dp)
                .background(Color.Red),
            contentAlignment = if (isStart) {
                Alignment.TopStart
            } else {
                Alignment.BottomStart
            }
        ) {
            Text(
                text = "AAA",
                textAlign = TextAlign.End
            )
        }
        Box(
            modifier = Modifier
            .width(100.dp)
            .height(200.dp)
            .background(Color.Blue)
        )
    }
}