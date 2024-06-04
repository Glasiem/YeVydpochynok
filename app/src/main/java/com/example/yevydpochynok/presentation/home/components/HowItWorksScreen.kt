package com.example.yevydpochynok.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yevydpochynok.R

@Composable
fun HowItWorksScreen(
    navigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "Як це працює",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                )
            },
            backgroundColor = Color.White,
            elevation = 0.dp,
            navigationIcon = {
                IconButton(onClick = navigateUp){
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_small_left_3),
                        contentDescription = null
                    )
                }
            }
        )

        Text(
            text = "Насправді, все дуже просто! Прочитайте коротку інструкцію внизу для повного освоєння всіх функцій застосунку!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Section(
            title = "1. Оберіть своє місто",
            content = "Для початку роботи із застосунком єВідпочинок, важливо обрати де ви знаходитесь. Для цього перейдіть на сторінку 'Моя локація' і оберіть ваше місто у списку. Тепер всі заклади, що Ви шукатимете, будуть відображатись по вашому обраному місту."
        )

        Section(
            title = "2. Категорії відпочинку",
            content = "На екрані категорій оберіть бажану. Застосовуйте фільтри до категорії щоб обрати саме той заклад, який ви хочете відвідати. Щоб відкрити повну інформацію про заклад, натисніть на нього. З сторінки детальної інформації, ви зможете перейти на веб-сайт закладу, побудувати до нього маршрут, побачити меню і години роботи (за наявності)."
        )

        Section(
            title = "3. План Відпочинку",
            content = "Щоб отримати готовий план відпочинку на день, вам потрібно пройти опитування вподобань, щоб наша система зрозуміла, як саме ви хочете провести дозвілля. Відповідайте чесно, і тоді персоналізований план вам точно сподобається!"
        )

        Section(
            title = "4. Наш веб-сайт",
            content = "Також рекомендуємо відвідати наш інформаційний веб-сайт! Там ви зможете прочитати політику конфіденційності застосунку, та іншу корисну інформацію про проєкт!"
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}


@Composable
fun Section(title: String, content: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = content,
            fontSize = 18.sp,
            color = Color.Black.copy(alpha = 0.9f)
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
}
