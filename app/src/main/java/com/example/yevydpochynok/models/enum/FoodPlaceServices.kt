package com.example.yevydpochynok.models.enum

data class FoodPlaceServices(
    val internalValue: String,
    val displayName: String
) {
    companion object {
        fun getFoodPlaceServices(): List<FoodPlaceServices> {
            return listOf(
                FoodPlaceServices(internalValue = "DELIVERY", displayName = "Доставка"),
                FoodPlaceServices(internalValue = "CHILDMENU", displayName = "Дитяче меню"),
                FoodPlaceServices(internalValue = "CHILD_ROOM", displayName = "Дитяча кімната"),
                FoodPlaceServices(internalValue = "BOARDGAMES", displayName = "Настільні ігри")
            )
        }
    }
}