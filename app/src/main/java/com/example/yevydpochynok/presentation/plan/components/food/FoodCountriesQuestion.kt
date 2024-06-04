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
import com.example.yevydpochynok.models.enum.FoodPlaceFoodCountries
import com.example.yevydpochynok.presentation.plan.PlanViewModel
import com.example.yevydpochynok.presentation.plan.components.AnswerRow

@Composable
fun FoodCountriesQuestion(
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
            text = "Оберіть декілька країн їжі які вам найбільше до смаку! Не обирайте жоден елемент якщо цей параметр для вас неважливий.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        FoodPlaceFoodCountries.getFoodPlaceFoodCountries().forEach { country ->
            AnswerRow(
                isSelected = selectedValues.contains(country.internalValue),
                text = country.displayName,
                onSelect = {
                    selectedValues = if (selectedValues.contains(country.internalValue)) {
                        selectedValues - country.internalValue
                    } else {
                        selectedValues + country.internalValue
                    }
                }
            )
        }
        Button(
            onClick = {
                viewModel.send(PlanViewModel.Event.OnFoodCountriesAnswer(selectedValues))
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
