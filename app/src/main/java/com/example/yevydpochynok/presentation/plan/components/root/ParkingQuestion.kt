package com.example.yevydpochynok.presentation.plan.components.root

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.yevydpochynok.presentation.plan.PlanViewModel
import com.example.yevydpochynok.presentation.plan.components.AnswerRow

@Composable
fun ParkingQuestion(
    viewModel: PlanViewModel,
    nextQuestion: () -> Unit
) {
    var selectedAnswer by remember { mutableStateOf<Int?>(null) }
    var selectedValue by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Чи важливе для вас зручне паркування біля місць відпочинку? У випадку пересування містом на власному автомобілі.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
        AnswerRow(isSelected = selectedAnswer == 0, text = "Так, важливо", onSelect = {
            selectedAnswer = 0
            selectedValue = true
        })
        AnswerRow(isSelected = selectedAnswer == 1, text = "Ні, не важливо", onSelect = {
            selectedAnswer = 1
            selectedValue = false
        })
        AnswerRow(isSelected = selectedAnswer == 2, text = "Без різниці", onSelect = {
            selectedAnswer = 2
            selectedValue = false
        })
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.send(PlanViewModel.Event.OnParkingAnswer(selectedValue))
                viewModel.send(PlanViewModel.Event.NextQuestion)
                nextQuestion()
            },
            enabled = selectedAnswer != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (selectedAnswer == null) Color.Gray else Color(0xFF00B22D)
            )
        ) {
            Text(
                text = "Далі",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}