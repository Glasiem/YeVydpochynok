package com.example.yevydpochynok.models.enum

data class FoodPlaceFoodTypes(
    val internalValue: String,
    val displayName: String
) {
    companion object {
        fun getFoodPlaceFoodTypes(): List<FoodPlaceFoodTypes> {
            return listOf(
                FoodPlaceFoodTypes(internalValue = "FASTFOOD", displayName = "Фастфуд"),
                FoodPlaceFoodTypes(internalValue = "SEAFOOD", displayName = "Морська їжа"),
                FoodPlaceFoodTypes(internalValue = "HEALTHY", displayName = "Здорова їжа"),
                FoodPlaceFoodTypes(internalValue = "VEGETARIAN", displayName = "Вегетеріанська їжа"),
                FoodPlaceFoodTypes(internalValue = "VEGAN", displayName = "Веганська їжа"),
                FoodPlaceFoodTypes(internalValue = "MEAT", displayName = "М'ясна їжа"),
                FoodPlaceFoodTypes(internalValue = "FISH", displayName = "Рибна їжа"),
                FoodPlaceFoodTypes(internalValue = "DESSERTS", displayName = "Десерти")
            )
        }
    }
}