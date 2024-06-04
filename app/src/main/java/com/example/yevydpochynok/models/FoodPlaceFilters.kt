package com.example.yevydpochynok.models

import kotlinx.serialization.Serializable

@Serializable
class FoodPlaceFilters (
    val placeTypes: List<String>,
    val foodCountries: List<String>,
    val foodTypes: List<String>,
    val services: List<String>,
    val alcohol: Boolean,
    val vip: Boolean,
    val parking: Boolean,
    val finePlaced: Boolean,
    val animalsAllowed: Boolean,
    val handicapAccessibility: Boolean,
    var city: String
) {
    companion object {
      fun getDefault(city: String? = null): FoodPlaceFilters {
        return FoodPlaceFilters(placeTypes = emptyList(),
            foodCountries = emptyList(),
            foodTypes = emptyList(),
            services = emptyList(),
            alcohol = false,
            vip = false,
            parking = false,
            finePlaced = false,
            animalsAllowed = false,
            handicapAccessibility = false,
            city = city ?: "Kyiv")
      }
    }
}
