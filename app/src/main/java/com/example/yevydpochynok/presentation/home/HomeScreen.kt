package com.example.yevydpochynok.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yevydpochynok.R
import com.example.yevydpochynok.presentation.Dimens.MediumPadding1
import com.example.yevydpochynok.presentation.Dimens.SmallPadding1
import com.example.yevydpochynok.presentation.home.components.CategoriesButton
import com.example.yevydpochynok.presentation.home.components.HomeScreenButton
import com.example.yevydpochynok.presentation.home.components.PlanButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    event: (HomeEvent) -> Unit,
    navigateToPlan: () -> Unit,
    navigateToLocation: () -> Unit,
    navigateToCategories: () -> Unit,
    navigateToWebsite: () -> Unit,
    navigateToAbout: () -> Unit
) {
    val configuration = LocalConfiguration.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Box(
            modifier = with (Modifier){
                fillMaxWidth()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.home_bg),
                        contentScale = ContentScale.Crop)
                    .size(configuration.screenWidthDp.dp)
            })
        {
            // Add more views here!
            Column (
                modifier = Modifier.align(Alignment.BottomStart)
                    .padding(start = SmallPadding1, bottom = MediumPadding1, end = SmallPadding1)
            ) {
                Text(
                    "Плануй відпочинок разом з єВідпочинок!",
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text("Створюй персоналізований план відпочинку відповідно ваших вподобань за лічені секунди!",
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 16.sp
                    )
                PlanButton(navigateToPlan,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    isSmall = false
                    )
            }
        }
        LazyColumn {
            item{
                CategoriesButton(navigateToCategories,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = SmallPadding1, end = SmallPadding1))
            }
            item {
                Row (modifier = Modifier.fillMaxHeight(0.5f))
                {
                    HomeScreenButton("Веб-сайт",
                        painterResource(id = R.drawable.site_alt),
                        navigateToWebsite,
                        modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.5f)
                            .padding(start = SmallPadding1, end = SmallPadding1)
                    )
                    HomeScreenButton("План відпочинку",
                        painterResource(id = R.drawable.description_alt),
                        navigateToPlan,
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)
                            .padding(end = SmallPadding1)
                    )
                }
                Spacer(modifier = Modifier.size(5.dp))
            }
            item{
                Row (modifier = Modifier.fillMaxHeight())
                {
                    HomeScreenButton("Як це працює",
                        painterResource(id = R.drawable.question),
                        navigateToAbout,
                        modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight()
                            .padding(start = SmallPadding1, end = SmallPadding1)
                    )
                    HomeScreenButton("Моя локація",
                        painterResource(id = R.drawable.land_layer_location),
                        navigateToLocation,
                        modifier = Modifier.fillMaxWidth().fillMaxHeight()
                            .padding(end = SmallPadding1)
                    )
                }
            }
        }
    }
}