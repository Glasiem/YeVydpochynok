package com.example.yevydpochynok.presentation.categories.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ParkPlaceFilterScreen (viewModel: ParkPlaceFilterVM = hiltViewModel(), dismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        header(viewModel, dismiss = dismiss)

        Divider()

        Text(
            text = "Фільтри",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(1.0f)
        ) {
            accessibilityFilters(viewModel)
            ApplyFiltersButton(viewModel = viewModel, dismiss = dismiss)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
private fun header(viewModel: ParkPlaceFilterVM, dismiss: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                viewModel.send(ParkPlaceFilterVM.Event.OnFiltersClear)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D))
        ) {
            Text(text = "Очистити",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { dismiss() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D))
        ) {
            Text(text = "Закрити",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun accessibilityFilters(viewModel: ParkPlaceFilterVM) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        FilterSwitch(
            isChecked = viewModel.playground.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnTogglePlayground(it)) },
            text = "Є дитячі майданчики"
        )
        FilterSwitch(
            isChecked = viewModel.hiking.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnToggleHiking(it)) },
            text = "Хайкінг"
        )
        FilterSwitch(
            isChecked = viewModel.sport.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnToggleSport(it)) },
            text = "Є спортмайданчики"
        )
        FilterSwitch(
            isChecked = viewModel.picnic.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnTogglePicnic(it)) },
            text = "Можна влаштувати пікнік"
        )
        FilterSwitch(
            isChecked = viewModel.cycling.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnToggleCycling(it)) },
            text = "Дозволено велопрогулянки"
        )
        FilterSwitch(
            isChecked = viewModel.finePlaced.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnToggleFinePlaced(it)) },
            text = "Зручне розсташування"
        )
        FilterSwitch(
            isChecked = viewModel.parking.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnToggleParking(it)) },
            text = "Зручне паркування"
        )
        FilterSwitch(
            isChecked = viewModel.animalsAllowed.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnToggleAnimalsAllowed(it)) },
            text = "Відвідування з тваринами"
        )
        FilterSwitch(
            isChecked = viewModel.handicapAccessibility.value,
            onCheckedChange = { viewModel.send(ParkPlaceFilterVM.Event.OnToggleHandicapAccessibility(it)) },
            text = "Пристосовано для інвалідів"
        )
    }
}

@Composable
private fun FilterSwitch(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}

@Composable
private fun ApplyFiltersButton(viewModel: ParkPlaceFilterVM, dismiss: () -> Unit) {
    Button(
        onClick = {
            viewModel.send(ParkPlaceFilterVM.Event.OnFiltersSubmit)
            dismiss()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color.Green, shape = RoundedCornerShape(10.dp))
    ) {
        Text("Застосувати", color = Color.White)
    }
}