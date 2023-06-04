package com.example.recipevault.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipevault.navigation.RecipeVaultAppRouter
import com.example.recipevault.navigation.Screen
import com.example.recipevault.navigation.SystemBackButtonHandler
import com.example.recipevault.ui.theme.BlueViolet1
import kotlinx.coroutines.*
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen() {
    var isRunning by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BlueViolet1
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SystemBackButtonHandler {
                RecipeVaultAppRouter.navigateTo(Screen.HomeScreen)
            }
            var totalSeconds by remember { mutableStateOf(0) }

            OutlinedTextField(
                value = formatTime(totalSeconds),
                onValueChange = { text ->
                    val time = parseTime(text)
                    totalSeconds = max(time, 0)
                },
                label = { Text("Set Time (mm:ss)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )

            )

            CountdownControl(
                isRunning = isRunning,
                onStart = { isRunning = true },
                onStop = { isRunning = false },
                coroutineScope = rememberCoroutineScope(),
                totalSeconds = totalSeconds
            )

            Spacer(modifier = Modifier.height(16.dp))

            val countdownText = formatTime(totalSeconds)
            Text(text = countdownText)
        }

    }

}

@Composable
fun CountdownControl(
    isRunning: Boolean,
    onStart: () -> Unit,
    onStop: () -> Unit,
    coroutineScope: CoroutineScope,
    totalSeconds: Int
) {
    Button(
        onClick = {
            if (isRunning) {
                onStop()
            } else {
                onStart()
                coroutineScope.launch {
                    startCountdown(totalSeconds)
                }
            }
        }
    ) {
        Text(text = if (isRunning) "Stop" else "Start")
    }
}

suspend fun startCountdown(totalSeconds: Int) {
    var remainingSeconds = totalSeconds
    withContext(Dispatchers.Default) {
        repeat(totalSeconds) {
            delay(1000)
            remainingSeconds--
        }
    }
}

private fun formatTime(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

private fun parseTime(timeString: String): Int {
    val components = timeString.split(":")
    if (components.size != 2) {
        return 0
    }
    val minutes = components[0].toIntOrNull() ?: 0
    val seconds = components[1].toIntOrNull() ?: 0
    return (minutes * 60) + seconds
}



@Preview
@Composable
fun DefaultPreviewOfTimerScreen() {
    TimerScreen()
}