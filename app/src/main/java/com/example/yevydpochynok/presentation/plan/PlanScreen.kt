package com.example.yevydpochynok.presentation.plan

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.example.yevydpochynok.models.Place
import com.example.yevydpochynok.presentation.plan.components.QuestioningResult
import com.example.yevydpochynok.presentation.plan.components.StartQuestioning
import com.example.yevydpochynok.presentation.plan.components.food.FoodCountriesQuestion
import com.example.yevydpochynok.presentation.plan.components.food.FoodTypesQuestion
import com.example.yevydpochynok.presentation.plan.components.food.PlaceTypesQuestion
import com.example.yevydpochynok.presentation.plan.components.food.ServicesQuestion
import com.example.yevydpochynok.presentation.plan.components.museum.ThematicQuestion
import com.example.yevydpochynok.presentation.plan.components.park.ParkQuestion
import com.example.yevydpochynok.presentation.plan.components.root.AlcoholQuestion
import com.example.yevydpochynok.presentation.plan.components.root.AnimalsAllowedQuestion
import com.example.yevydpochynok.presentation.plan.components.root.FinePlacedQuestion
import com.example.yevydpochynok.presentation.plan.components.root.HandicapQuestion
import com.example.yevydpochynok.presentation.plan.components.root.ParkingQuestion

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanScreen(
    viewModel: PlanViewModel,
    goToDetails: (Place) -> Unit,
    nextQuestion: () -> Unit
){

//    Log.d("AAAAAAAAAAAAAA0", UserData.getInstance().getQuestionIndex().toString())
//    Log.d("AAAAAAAAAAAAAA1", viewModel.questionIndex.value.toString())
//    Log.d("AAAAAAAAAAAAAA2", viewModel.planFilters.value!!.alcohol.toString())
//    Log.d("AAAAAAAAAAAAAA3", UserData.getInstance().plan?.foodModels?.count().toString())
//    Log.d("AAAAAAAAAAAAAA4", UserData.getInstance().foodPlace?.name.toString())
//    Log.d("AAAAAAAAAAAAAA1", viewModel.isQuizFinished.value.toString())

    if (viewModel.isQuizFinished.value!!) {
        QuestioningResult(viewModel, goToDetails, nextQuestion)
    } else {
        when (viewModel.questionIndex.value) {
            0 -> StartQuestioning(viewModel, nextQuestion)
            1 -> AlcoholQuestion(viewModel, nextQuestion)
            2 -> ParkingQuestion(viewModel, nextQuestion)
            3 -> HandicapQuestion(viewModel, nextQuestion)
            4 -> FinePlacedQuestion(viewModel, nextQuestion)
            5 -> AnimalsAllowedQuestion(viewModel, nextQuestion)
            6 -> PlaceTypesQuestion(viewModel, nextQuestion)
            7 -> FoodCountriesQuestion(viewModel, nextQuestion)
            8 -> FoodTypesQuestion(viewModel, nextQuestion)
            9 -> ServicesQuestion(viewModel, nextQuestion)
            10 -> ParkQuestion(viewModel, nextQuestion)
            11 -> ThematicQuestion(viewModel, nextQuestion)
        }
    }

}