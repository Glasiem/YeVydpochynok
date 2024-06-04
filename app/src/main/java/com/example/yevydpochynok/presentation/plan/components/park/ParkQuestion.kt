package com.example.yevydpochynok.presentation.plan.components.park

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yevydpochynok.presentation.plan.PlanViewModel
import com.example.yevydpochynok.presentation.plan.components.AnswerRow

@Composable
fun ParkQuestion(
    viewModel: PlanViewModel,
    nextQuestion: () -> Unit
) {
    var selectedValueSport by remember { mutableStateOf(false) }
    var selectedValueHiking by remember { mutableStateOf(false) }
    var selectedValueCycling by remember { mutableStateOf(false) }
    var selectedValuePlayground by remember { mutableStateOf(false) }
    var selectedValuePicnic by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Оберіть декілька з зручностей які для вас важливі при відвідуванні парків.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        AnswerRow(
            isSelected = selectedValueSport,
            text = "Cпортивний майданчик",
            onSelect = { selectedValueSport = !selectedValueSport }
        )

        AnswerRow(
            isSelected = selectedValueHiking,
            text = "Хайкінг",
            onSelect = { selectedValueHiking = !selectedValueHiking }
        )

        AnswerRow(
            isSelected = selectedValueCycling,
            text = "Велопрогулянки",
            onSelect = { selectedValueCycling = !selectedValueCycling }
        )

        AnswerRow(
            isSelected = selectedValuePlayground,
            text = "Дитячий майданчик",
            onSelect = { selectedValuePlayground = !selectedValuePlayground }
        )

        AnswerRow(
            isSelected = selectedValuePicnic,
            text = "Дозволено пікніки",
            onSelect = { selectedValuePicnic = !selectedValuePicnic }
        )


        ButtonNext {
            viewModel.send(PlanViewModel.Event.OnSportAnswer(selectedValueSport))
            viewModel.send(PlanViewModel.Event.OnHikingAnswer(selectedValueHiking))
            viewModel.send(PlanViewModel.Event.OnCyclingAnswer(selectedValueCycling))
            viewModel.send(PlanViewModel.Event.OnPlaygroundAnswer(selectedValuePlayground))
            viewModel.send(PlanViewModel.Event.OnPicnicAnswer(selectedValuePicnic))
            viewModel.send(PlanViewModel.Event.NextQuestion)
            nextQuestion()
        }
    }
}

@Composable
fun ButtonNext(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D))
    ) {
        Text(
            text = "Далі",
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
