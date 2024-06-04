package com.example.yevydpochynok.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yevydpochynok.R

@Composable
fun HomeScreenButton(label: String, icon: Painter, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(vertical = 8.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = icon, contentDescription = label, modifier = Modifier.size(24.dp).padding(bottom = 8.dp))
            Text(text = label, color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}