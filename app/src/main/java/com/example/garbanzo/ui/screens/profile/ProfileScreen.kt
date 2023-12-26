package com.example.garbanzo.ui.screens.profile


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

@Composable
fun ProfileScreen(name: String, onClick: () -> Unit) {

    val viewModel: ProfileViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = RandomColor.getColor()),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            if (uiState.errorMessage != null) {
                Text(
                    modifier = Modifier.clickable { onClick() },
                    text = uiState.errorMessage!!,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    fontWeight = FontWeight.Bold
                )
            } else {
                uiState.messages.forEach { hashMap ->
                    hashMap.forEach {
                        Text(
                            modifier = Modifier.clickable { onClick() },
                            text = it.key,
                            fontSize = MaterialTheme.typography.body1.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

object RandomColor {
    private val colors = listOf(
        Color.Cyan,
        Color.Red,
        Color.Gray,
        Color.Yellow,
        Color.Gray,
        Color.Black,
        Color.Blue,
        Color.Magenta
    )

    fun getColor(): Color {
        return colors[Random.nextInt(colors.size - 1)]
    }
}