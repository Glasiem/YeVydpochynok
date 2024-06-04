package com.example.yevydpochynok.presentation.plan.components.food

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yevydpochynok.models.enum.FoodPlaceType
import com.example.yevydpochynok.presentation.plan.PlanViewModel
import com.example.yevydpochynok.presentation.plan.components.AnswerRow

@Composable
fun PlaceTypesQuestion(
    viewModel: PlanViewModel,
    nextQuestion: () -> Unit
) {
    var selectedValues by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Оберіть декілька типів ресторанів, які ви найбільше хотіли б відвідати! Не обирайте жоден елемент якщо цей параметр для вас неважливий.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        FoodPlaceType.getFoodPlaceTypes().forEach { type ->
            AnswerRow(
                isSelected = selectedValues.contains(type.internalValue),
                text = type.displayName,
                onSelect = {
                    selectedValues = if (selectedValues.contains(type.internalValue)) {
                        selectedValues - type.internalValue
                    } else {
                        selectedValues + type.internalValue
                    }
                }
            )
        }
        Button(
            onClick = {
                viewModel.send(PlanViewModel.Event.OnPlaceTypesAnswer(selectedValues))
                viewModel.send(PlanViewModel.Event.NextQuestion)
                nextQuestion()
            },
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
}