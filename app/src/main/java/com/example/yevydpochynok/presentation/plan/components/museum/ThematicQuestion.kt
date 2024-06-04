package com.example.yevydpochynok.presentation.plan.components.museum

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
import com.example.yevydpochynok.models.enum.MuseumTheme
import com.example.yevydpochynok.presentation.plan.PlanViewModel
import com.example.yevydpochynok.presentation.plan.components.AnswerRow

@Composable
fun ThematicQuestion(
    viewModel: PlanViewModel,
    nextQuestion: () -> Unit
) {
    var selectedValue by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Оберіть одну бажану тематику музею яка вам найбільше до вподоби!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        MuseumTheme.getMuseumThematics().forEach { thematic ->
            AnswerRow(
                isSelected = selectedValue == thematic.internalValue,
                text = thematic.displayName,
                onSelect = {
                    selectedValue = thematic.internalValue
                }
            )
        }

        ButtonNext(
            onClick = {
                viewModel.send(PlanViewModel.Event.OnThematicAnswer(selectedValue))
                viewModel.send(PlanViewModel.Event.NextQuestion)
                nextQuestion()
            },
            enabled = selectedValue != null
        )
    }
}


@Composable
fun ButtonNext(onClick: () -> Unit, enabled: Boolean) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = if (enabled) Color(0xFF00B22D) else Color.Gray)
    ) {
        Text(
            text = "Далі",
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}