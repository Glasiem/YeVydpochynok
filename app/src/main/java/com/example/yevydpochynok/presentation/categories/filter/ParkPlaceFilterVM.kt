package com.example.yevydpochynok.presentation.categories.filter

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.models.FoodPlaceFilters
import com.example.yevydpochynok.models.ParkPlaceFilters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ParkPlaceFilterVM @Inject constructor(
) : ViewModel() {

    val playground = mutableStateOf(false)
    val hiking = mutableStateOf(false)
    val sport = mutableStateOf(false)
    val picnic = mutableStateOf(false)
    val cycling = mutableStateOf(false)
    val finePlaced = mutableStateOf(false)
    val parking = mutableStateOf(false)
    val animalsAllowed = mutableStateOf(false)
    val handicapAccessibility = mutableStateOf(false)

    init {
        playground.value = UserData.getInstance().getParkPlaceFilter().playground
        hiking.value = UserData.getInstance().getParkPlaceFilter().hiking
        sport.value = UserData.getInstance().getParkPlaceFilter().sport
        picnic.value = UserData.getInstance().getParkPlaceFilter().picnic
        cycling.value = UserData.getInstance().getParkPlaceFilter().cycling
        finePlaced.value = UserData.getInstance().getParkPlaceFilter().finePlaced
        parking.value = UserData.getInstance().getParkPlaceFilter().parking
        animalsAllowed.value = UserData.getInstance().getParkPlaceFilter().animalsAllowed
        handicapAccessibility.value = UserData.getInstance().getParkPlaceFilter().handicapAccessibility
    }

    fun send(event: Event) {
        when (event) {
            Event.OnAppear -> println("FilterView Appear Event")
            Event.OnFiltersClear -> clearFilters()
            Event.OnFiltersSubmit -> submitFilters()
            is Event.OnTogglePlayground -> onTogglePlayground(event.toggle)
            is Event.OnToggleHiking -> onToggleHiking(event.toggle)
            is Event.OnToggleSport -> onToggleSport(event.toggle)
            is Event.OnTogglePicnic -> onTogglePicnic(event.toggle)
            is Event.OnToggleCycling -> onToggleCycling(event.toggle)
            is Event.OnToggleFinePlaced -> onToggleFinePlaced(event.toggle)
            is Event.OnToggleParking -> onToggleParking(event.toggle)
            is Event.OnToggleAnimalsAllowed -> onToggleAnimalsAllowed(event.toggle)
            is Event.OnToggleHandicapAccessibility -> onToggleHandicapAccessibility(event.toggle)
        }
    }

    private fun onTogglePlayground(toggle: Boolean) {
        playground.value = toggle
    }

    private fun onToggleHiking(toggle: Boolean) {
        hiking.value = toggle
    }

    private fun onToggleSport(toggle: Boolean) {
        sport.value = toggle
    }

    private fun onTogglePicnic(toggle: Boolean) {
        picnic.value = toggle
    }

    private fun onToggleCycling(toggle: Boolean) {
        cycling.value = toggle
    }

    private fun onToggleFinePlaced(toggle: Boolean) {
        finePlaced.value = toggle
    }

    private fun onToggleParking(toggle: Boolean) {
        parking.value = toggle
    }

    private fun onToggleAnimalsAllowed(toggle: Boolean) {
        animalsAllowed.value = toggle
    }

    private fun onToggleHandicapAccessibility(toggle: Boolean) {
        handicapAccessibility.value = toggle
    }

    private fun submitFilters() {
        val city = UserData.getInstance().getCity() ?: "Kyiv"
        var parkPlaceFilters = ParkPlaceFilters(
            playground = playground.value,
            picnic = picnic.value,
            hiking = hiking.value,
            sport = sport.value,
            cycling = cycling.value,
            city = city,
            finePlaced = finePlaced.value,
            parking = parking.value,
            animalsAllowed = animalsAllowed.value,
            handicapAccessibility = handicapAccessibility.value
        )
        UserData.getInstance().setParkPlaceFilter(parkPlaceFilters)
    }

    private fun clearFilters() {
        UserData.getInstance().setFoodPlaceFilter(FoodPlaceFilters.getDefault(UserData.getInstance().getCity()))
        playground.value = UserData.getInstance().getParkPlaceFilter().playground
        hiking.value = UserData.getInstance().getParkPlaceFilter().hiking
        sport.value = UserData.getInstance().getParkPlaceFilter().sport
        picnic.value = UserData.getInstance().getParkPlaceFilter().picnic
        cycling.value = UserData.getInstance().getParkPlaceFilter().cycling
        finePlaced.value = UserData.getInstance().getParkPlaceFilter().finePlaced
        parking.value = UserData.getInstance().getParkPlaceFilter().parking
        animalsAllowed.value = UserData.getInstance().getParkPlaceFilter().animalsAllowed
        handicapAccessibility.value = UserData.getInstance().getParkPlaceFilter().handicapAccessibility
    }

    sealed class Event {
        object OnAppear : Event()
        object OnFiltersClear : Event()
        object OnFiltersSubmit : Event()
        data class OnTogglePlayground(val toggle: Boolean) : Event()
        data class OnToggleHiking(val toggle: Boolean) : Event()
        data class OnToggleSport(val toggle: Boolean) : Event()
        data class OnTogglePicnic(val toggle: Boolean) : Event()
        data class OnToggleCycling(val toggle: Boolean) : Event()
        data class OnToggleFinePlaced(val toggle: Boolean) : Event()
        data class OnToggleParking(val toggle: Boolean) : Event()
        data class OnToggleAnimalsAllowed(val toggle: Boolean) : Event()
        data class OnToggleHandicapAccessibility(val toggle: Boolean) : Event()
    }
}