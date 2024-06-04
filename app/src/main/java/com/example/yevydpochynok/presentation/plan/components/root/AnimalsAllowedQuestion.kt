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
fun AnimalsAllowedQuestion(
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
            text = "Чи плануєте ви проводити час відпочинку з домашніми улюбленцями? Оберіть чи важливо щоб місця відпочинку дозволяли вхід з тваринками.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
        AnswerRow(isSelected = selectedAnswer == 0, text = "Так, візьму з собою шерстяного", onSelect = {
            selectedAnswer = 0
            selectedValue = true
        })
        AnswerRow(isSelected = selectedAnswer == 1, text = "Ні, відпочиваю без тварин", onSelect = {
            selectedAnswer = 1
            selectedValue = false
        })
        AnswerRow(isSelected = selectedAnswer == 2, text = "Не має значення/Немає тваринок", onSelect = {
            selectedAnswer = 2
            selectedValue = false
        })
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.send(PlanViewModel.Event.OnAnimalsAllowedAnswer(selectedValue))
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