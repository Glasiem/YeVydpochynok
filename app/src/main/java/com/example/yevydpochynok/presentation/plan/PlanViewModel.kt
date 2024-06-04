package com.example.yevydpochynok.presentation.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.models.Place
import com.example.yevydpochynok.models.RestPlan
import com.example.yevydpochynok.models.RestPlanFilters
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
) : ViewModel() {
    val questionIndex = MutableLiveData(UserData.getInstance().getQuestionIndex())
//    val questionIndex: LiveData<Int> get() = _questionIndex

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isQuizFinished = MutableLiveData(UserData.getInstance().getIsQuizFinished())
    val isQuizFinished: LiveData<Boolean> get() = _isQuizFinished

    private val _planFilters = MutableLiveData(UserData.getInstance().planFilters)
    val planFilters: LiveData<RestPlanFilters> get() = _planFilters

    private val _plan = MutableLiveData<RestPlan?>(UserData.getInstance().plan)
    val plan: LiveData<RestPlan?> get() = _plan

    private val _foodPlace = MutableLiveData<Place?>(UserData.getInstance().foodPlace)
    val foodPlace: LiveData<Place?> get() = _foodPlace

    private val _parkPlace = MutableLiveData<Place?>(UserData.getInstance().parkPlace)
    val parkPlace: LiveData<Place?> get() = _parkPlace

    private val _museumPlace = MutableLiveData<Place?>(UserData.getInstance().museumPlace)
    val museumPlace: LiveData<Place?> get() = _museumPlace

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            gson()
        }
    }

    fun send(event: Event) {
        when (event) {
            Event.NextQuestion -> {
                questionIndex.value = (questionIndex.value ?: 0) + 1
                UserData.getInstance().setQuestionIndex(questionIndex.value!!)
                if ((questionIndex.value ?: 0) > 11) {
                    _isQuizFinished.value = true
                    UserData.getInstance().setIsQuizFinished(true)
                    fetchRestPlan(_planFilters.value ?: RestPlanFilters.getDefault())
                }
            }
            Event.OnAppear -> {
                val city = UserData.getInstance().getCity()
                _planFilters.value = _planFilters.value?.copy(city = city)
            }
            is Event.OnParkingAnswer -> UserData.getInstance().planFilters.parking = event.value
            is Event.OnAnimalsAllowedAnswer -> UserData.getInstance().planFilters.animalsAllowed = event.value
            is Event.OnHandicapAccAnswer -> UserData.getInstance().planFilters.handicapAccessibility = event.value
            is Event.OnAlcoholAnswer -> UserData.getInstance().planFilters.alcohol = event.value
            is Event.OnPlaygroundAnswer -> UserData.getInstance().planFilters.playground = event.value
            is Event.OnPicnicAnswer -> UserData.getInstance().planFilters.picnic = event.value
            is Event.OnHikingAnswer -> UserData.getInstance().planFilters.hiking = event.value
            is Event.OnSportAnswer -> UserData.getInstance().planFilters.sport = event.value
            is Event.OnCyclingAnswer -> UserData.getInstance().planFilters.cycling = event.value
            is Event.OnFinePlacedAnswer -> UserData.getInstance().planFilters.finePlaced = event.value
            is Event.OnThematicAnswer -> UserData.getInstance().planFilters.thematic = event.value
            is Event.OnPlaceTypesAnswer -> UserData.getInstance().planFilters.placeTypes = event.values
            is Event.OnFoodCountriesAnswer -> UserData.getInstance().planFilters.foodCountries = event.values
            is Event.OnFoodTypesAnswer -> UserData.getInstance().planFilters.foodTypes = event.values
            is Event.OnServicesAnswer -> UserData.getInstance().planFilters.services = event.values
            Event.Regenerate -> {
                UserData.getInstance().foodPlace = UserData.getInstance().plan?.foodModels?.randomOrNull()
                UserData.getInstance().parkPlace = UserData.getInstance().plan?.parkModels?.randomOrNull()
                UserData.getInstance().museumPlace = UserData.getInstance().plan?.museumModels?.randomOrNull()
            }
            Event.Restart -> {
                _isQuizFinished.value = false
                UserData.getInstance().setIsQuizFinished(false)
                questionIndex.value = 0
                UserData.getInstance().setQuestionIndex(questionIndex.value!!)
            }
        }
    }

    private fun fetchRestPlan(filters: RestPlanFilters) {
        val stringURL = "https://hereisrest.onrender.com/getSurveyResult";
        val gson = Gson()
        val httpBody = gson.toJson(filters)
        _isLoading.value = true
        UserData.getInstance().plan = runBlocking{
            var result = client.request{
                method = HttpMethod.Post
                url(stringURL)
                contentType(ContentType.Application.Json)
                setBody(httpBody)
            }.body<RestPlan>()
            result
        }
        UserData.getInstance().foodPlace = UserData.getInstance().plan?.foodModels?.firstOrNull()
        UserData.getInstance().parkPlace = UserData.getInstance().plan?.parkModels?.firstOrNull()
        UserData.getInstance().museumPlace = UserData.getInstance().plan?.museumModels?.firstOrNull()
        _isLoading.postValue(false)
    }

    sealed class Event {
        object NextQuestion : Event()
        object OnAppear : Event()
        data class OnParkingAnswer(val value: Boolean) : Event()
        data class OnAnimalsAllowedAnswer(val value: Boolean) : Event()
        data class OnHandicapAccAnswer(val value: Boolean) : Event()
        data class OnAlcoholAnswer(val value: Boolean) : Event()
        data class OnPlaygroundAnswer(val value: Boolean) : Event()
        data class OnPicnicAnswer(val value: Boolean) : Event()
        data class OnHikingAnswer(val value: Boolean) : Event()
        data class OnSportAnswer(val value: Boolean) : Event()
        data class OnCyclingAnswer(val value: Boolean) : Event()
        data class OnFinePlacedAnswer(val value: Boolean) : Event()
        data class OnThematicAnswer(val value: String?) : Event()
        data class OnPlaceTypesAnswer(val values: List<String>) : Event()
        data class OnFoodCountriesAnswer(val values: List<String>) : Event()
        data class OnFoodTypesAnswer(val values: List<String>) : Event()
        data class OnServicesAnswer(val values: List<String>) : Event()
        object Restart : Event()
        object Regenerate : Event()
    }
}