package com.example.yevydpochynok.presentation.categories.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.yevydpochynok.R
import com.example.yevydpochynok.models.Place


@Composable
fun PlaceDetails(
    place: Place,
    navigateUp: () -> Unit
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        TopAppBar(
            title = { Text(place.name, color = Color.Black) },
            navigationIcon = {
                IconButton(onClick = navigateUp){
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.arrow_small_left_3),
                        contentDescription = null
                    )
                }
            },
            backgroundColor = Color.White
        )
        if (place.images.isNotEmpty()) {
            Image(
                painter = rememberImagePainter(place.images.first()),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Зручності",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                tint = Color.Black
            )
            Text(
                text = String.format("%.1f", place.rating),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = when {
                    place.rating > 4.5 -> "Відмінно"
                    place.rating > 3.8 -> "Добре"
                    else -> "Неоднозначно..."
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            if (place.animalsAllowed) {
                Image(
                    painter = painterResource(id = R.drawable.paw),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RectangleShape)
                        .padding(horizontal = 8.dp)
                )
            }
            if (place.finePlaced) {
                Image(
                    painter = painterResource(id = R.drawable.land_location),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RectangleShape)
                        .padding(horizontal = 8.dp)
                )
            }
            if (place.handicapAccessibility) {
                Image(
                    painter = painterResource(id = R.drawable.wheelchair),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RectangleShape)
                        .padding(horizontal = 8.dp)
                )
            }
            if (place.paidVisit) {
                Image(
                    painter = painterResource(id = R.drawable.coins),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RectangleShape)
                        .padding(horizontal = 8.dp)
                )
            }
            if (place.parking) {
                Image(
                    painter = painterResource(id = R.drawable.parking_circle),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RectangleShape)
                        .padding(horizontal = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = { uriHandler.openUri(Uri.parse(place.location).toString()) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D)),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Маршрут",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Button(
                onClick = { uriHandler.openUri(Uri.parse(place.website).toString()) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D)),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Меню",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = { uriHandler.openUri(Uri.parse(place.website).toString()) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D)),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Веб-сайт",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Button(
                onClick = { uriHandler.openUri(Uri.parse(place.location).toString()) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D)),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Часи роботи",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Галерея",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            place.images.forEach { image ->
                Image(
                    painter = rememberImagePainter(image),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RectangleShape)
                        .padding(horizontal = 8.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Опис",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = place.description,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 5.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Адреса",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = place.address,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 5.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}