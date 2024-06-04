package com.example.yevydpochynok.models.enum


data class FoodPlaceFoodCountries(
    val internalValue: String,
    val displayName: String
) {
    companion object {
        fun getFoodPlaceFoodCountries(): List<FoodPlaceFoodCountries> {
            return listOf(
                FoodPlaceFoodCountries(internalValue = "AMERICAN", displayName = "Американська"),
                FoodPlaceFoodCountries(internalValue = "ASIAN", displayName = "Азійська"),
                FoodPlaceFoodCountries(internalValue = "UKRAINIAN", displayName = "Українська"),
                FoodPlaceFoodCountries(internalValue = "ITALIAN", displayName = "Італійська"),
                FoodPlaceFoodCountries(internalValue = "MEXICAN", displayName = "Мексиканська"),
                FoodPlaceFoodCountries(internalValue = "FRENCH", displayName = "Французька"),
                FoodPlaceFoodCountries(internalValue = "CHINESE", displayName = "Китайська"),
                FoodPlaceFoodCountries(internalValue = "JAPANESE", displayName = "Японська"),
                FoodPlaceFoodCountries(internalValue = "CRIMEAN_TATAR", displayName = "Кримсько-Татарська"),
                FoodPlaceFoodCountries(internalValue = "KOREAN", displayName = "Корейська"),
                FoodPlaceFoodCountries(internalValue = "GEORGIAN", displayName = "Грузинська"),
                FoodPlaceFoodCountries(internalValue = "EUROPEAN", displayName = "Європейська"),
                FoodPlaceFoodCountries(internalValue = "CAUCASIAN", displayName = "Кавказька"),
                FoodPlaceFoodCountries(internalValue = "SPANISH", displayName = "Іспанська")
            )
        }
    }
}

