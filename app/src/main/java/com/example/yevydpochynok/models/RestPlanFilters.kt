package com.example.yevydpochynok.models

data class RestPlanFilters(
    var placeTypes: List<String> = emptyList(),
    var foodCountries: List<String> = emptyList(),
    var foodTypes: List<String> = emptyList(),
    var services: List<String> = emptyList(),
    var playground: Boolean = false,
    var picnic: Boolean = false,
    var hiking: Boolean = false,
    var sport: Boolean = false,
    var cycling: Boolean = false,
    var thematic: String? = null,
    var alcohol: Boolean = false,
    var parking: Boolean = false,
    var finePlaced: Boolean = false,
    var animalsAllowed: Boolean = false,
    var handicapAccessibility: Boolean = false,
    var city: String = "Kyiv"
){
    companion object {
        fun getDefault(city: String? = null): RestPlanFilters {
            return RestPlanFilters(placeTypes = emptyList(), foodCountries = emptyList(), foodTypes = emptyList(),
                services = emptyList(), playground = false, picnic = false, hiking = false, sport = false,
                cycling = false, alcohol = false, thematic = null, parking = false, finePlaced = false,
                animalsAllowed = false, handicapAccessibility = false, city = city ?: "Kyiv")
        }
    }
}