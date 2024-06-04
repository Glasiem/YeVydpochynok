package com.example.yevydpochynok.models.enum

data class FoodPlaceType(
    val internalValue: String,
    val displayName: String
) {
    companion object {
        fun getFoodPlaceTypes(): List<FoodPlaceType> {
            return listOf(
                FoodPlaceType(internalValue = "RESTAURANT", displayName = "Ресторан"),
                FoodPlaceType(internalValue = "CAFE", displayName = "Кафе"),
                FoodPlaceType(internalValue = "BAR", displayName = "Бар"),
                FoodPlaceType(internalValue = "CAFE_BAR", displayName = "Кафе-бар"),
                FoodPlaceType(internalValue = "CAFETERIA", displayName = "Кафетерія"),
                FoodPlaceType(internalValue = "PUB", displayName = "Паб"),
                FoodPlaceType(internalValue = "CANTEEN", displayName = "Їдальня"),
                FoodPlaceType(internalValue = "PIZZERIA", displayName = "Піцерія"),
                FoodPlaceType(internalValue = "COFFEEHOUSE", displayName = "Кав'ярня"),
                FoodPlaceType(internalValue = "LOUNGE_BAR", displayName = "Лаунж-бар")
            )
        }

    }
}
