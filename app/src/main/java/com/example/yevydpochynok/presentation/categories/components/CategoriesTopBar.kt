package com.example.yevydpochynok.presentation.categories.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.yevydpochynok.R

@Composable
fun CategoriesTopBar(
    onBackClick: () -> Unit,
){
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick){
                Icon(
                    painter = painterResource(id = R.drawable.arrow_small_left_3),
                    contentDescription = null
                )
            }
        }
    )
}