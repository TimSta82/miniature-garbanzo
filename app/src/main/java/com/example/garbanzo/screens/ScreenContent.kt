package com.example.garbanzo.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlin.random.Random

@Composable
fun ScreenContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(color = RandomColor.getColor()),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = name,
            fontSize = MaterialTheme.typography.body1.fontSize,
            fontWeight = FontWeight.Bold
        )
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