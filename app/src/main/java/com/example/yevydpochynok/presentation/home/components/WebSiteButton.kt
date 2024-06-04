package com.example.yevydpochynok.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.yevydpochynok.R

@Composable
fun WebSiteButton(
    onClick: () -> Unit,
    modifier : Modifier
){
    Button(onClick = {onClick()}, modifier = modifier){
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                painter = painterResource(id = R.drawable.site_alt),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text("WebSite")
        }
    }
}