package com.example.yevydpochynok.presentation.plan.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yevydpochynok.R
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.models.Place
import com.example.yevydpochynok.models.PlaceView
import com.example.yevydpochynok.presentation.plan.PlanViewModel
import kotlinx.coroutines.launch

@Composable
fun QuestioningResult(
    viewModel: PlanViewModel = viewModel(),
    goToFoodDetails: (Place) -> Unit,
    nextQuestion: () -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TopAppBar(
            title = { Text("План дня",
                color = Color.Black) },
            backgroundColor = Color.White,
            actions = {
                IconButton(onClick = {
                    viewModel.send(PlanViewModel.Event.Regenerate)
                    nextQuestion()
                }) {
                    Icon(painterResource(id = R.drawable.ic_refresh_2), contentDescription = "Refresh")
                }
            }
        )
        UserData.getInstance().foodPlace?.let { foodPlace ->
            PlaceView(place = foodPlace, onClick = { goToFoodDetails(foodPlace) })
            Spacer(modifier = Modifier.height(16.dp))
            DividerDots()
            Spacer(modifier = Modifier.height(16.dp))
        }
        UserData.getInstance().parkPlace?.let { parkPlace ->
            PlaceView(place = parkPlace, onClick = { goToFoodDetails(parkPlace) })
            Spacer(modifier = Modifier.height(16.dp))
            DividerDots()
            Spacer(modifier = Modifier.height(16.dp))
        }
        UserData.getInstance().museumPlace?.let { museumPlace ->
            PlaceView(place = museumPlace, onClick = { goToFoodDetails(museumPlace) })
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.send(PlanViewModel.Event.Restart)
                nextQuestion()
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D))
        ) {
            Text(
                text = "Пройти опитування знову",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun DividerDots() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(5) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                tint = Color.Black, // Use the appropriate color
                modifier = Modifier
                    .size(5.dp)
                    .padding(horizontal = 2.dp)
            )
        }
    }
}