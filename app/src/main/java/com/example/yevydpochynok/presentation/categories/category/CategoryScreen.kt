package com.example.yevydpochynok.presentation.categories.category

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.yevydpochynok.R
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.models.Place
import com.example.yevydpochynok.models.PlaceView
import com.example.yevydpochynok.presentation.categories.category.components.CategoryTopBar
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(
    category: String,
    navigateUp: () -> Unit,
    viewModel: CategoryViewModel,
    goToFilters: () -> Unit,
    goToDetails: (Place) -> Unit
//    goToParkDetails: (Place) -> Unit,
//    goToMuseumDetails: (Place) -> Unit
){
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var places: List<Place>
    val screenCategory = Categories.valueOf(category)
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CategoryTopBar (onBackClick = navigateUp, goToFilters = goToFilters)
//        when (screenCategory){
//            Categories.RESTAURANT -> Text("Restaurant")
//            Categories.PARK -> Text("Park")
//            Categories.MUSEUM -> Text("Museum")
//        }
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth(),
            state = listState
        ){
            when (screenCategory){
                Categories.RESTAURANT -> {
                    places = viewModel.fetchFoodPlaces(UserData.getInstance().getFoodPlaceFilter())
                    Log.d("AAAAAAAAAAAAAA0", "CATEGORY SCREEN")
                    if (!viewModel.isLoading && viewModel.places.isEmpty()) {
                        item {
                            Text(
                                text = "Схоже, ми не знайшли закладів за вашими критеріями :(",
                                modifier = Modifier.padding(bottom = 20.dp),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        items(
                            count = places.count()
                        ) {
                            places[it]?.let { place ->
                                PlaceView(place, onClick = { goToDetails(place) })
                            }
                        }
                    }
                }
                Categories.PARK -> {
                    places = viewModel.fetchParkPlaces(UserData.getInstance().getParkPlaceFilter())
                    if (!viewModel.isLoading && viewModel.places.isEmpty()) {
                        item {
                            Text(
                                text = "Схоже, ми не знайшли закладів за вашими критеріями :(",
                                modifier = Modifier.padding(bottom = 20.dp),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        items(
                            count = places.count()
                        ) {
                            places[it]?.let { place ->
                                PlaceView(place, onClick = { goToDetails(place) })
                            }
                        }
                    }
                }
                Categories.MUSEUM -> {
                    places = viewModel.fetchMuseumPlaces(UserData.getInstance().getMuseumPlaceFilter())
                    if (!viewModel.isLoading && viewModel.places.isEmpty()) {
                        item {
                            Text(
                                text = "Схоже, ми не знайшли закладів за вашими критеріями :(",
                                modifier = Modifier.padding(bottom = 20.dp),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        items(
                            count = places.count()
                        ) {
                            places[it]?.let { place ->
                                PlaceView(place, onClick = { goToDetails(place) })
                            }
                        }
                    }
                }
            }
            item{
                Divider()
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (viewModel.page != 0) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_small_left_3),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .background(Color(0xFF00FF00))  // Replace with your color resource
                                .clickable {
                                    viewModel.prevPage()
                                    places = viewModel.fetchFoodPlaces(UserData.getInstance().getFoodPlaceFilter())
                                    scope.launch {
                                        listState.scrollToItem(0)
                                    }
                                }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Text(
                        text = "Cторінка ${viewModel.page + 1}",
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_small_right_3),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color(0xFF00FF00)) // Replace with your color resource
                            .clickable {
                                viewModel.nextPage()
                                places = viewModel.fetchFoodPlaces(UserData.getInstance().getFoodPlaceFilter())
                                scope.launch {
                                    listState.scrollToItem(0)
                                }
                            }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}