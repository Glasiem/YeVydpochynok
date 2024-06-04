package com.example.yevydpochynok.presentation.categories

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yevydpochynok.R
import com.example.yevydpochynok.presentation.Dimens
import com.example.yevydpochynok.presentation.categories.components.CategoriesTopBar
import com.example.yevydpochynok.presentation.plan.components.PlanTopBar

@Composable
fun CategoriesScreen(
    navigateUp: () -> Unit,
    navigateToRestaurants: () -> Unit,
    navigateToParks: () -> Unit,
    navigateToMuseums: () -> Unit,
){
    Log.d("AAAAAAAAAAAAAA0", "CATEGORIES SCREEN")
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CategoriesTopBar (onBackClick = navigateUp)
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
        ){
            item{
//                Text("Hello")
                Spacer(modifier = Modifier.height(5.dp))
                Text("Знайти за категорією",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = Dimens.SmallPadding1, end = Dimens.SmallPadding1)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item{
                Column (
                    modifier = Modifier.clickable { navigateToRestaurants.invoke() }
                        .padding(start = Dimens.SmallPadding1, end = Dimens.SmallPadding1)
                ){
                    Image(
                        painterResource(R.drawable.restaurant),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                        )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        "Ресторани",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Знайдіть найкращі місця де можна смачно поїсти",
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            item{
                Column (
                    modifier = Modifier.clickable { navigateToParks.invoke() }
                        .padding(start = Dimens.SmallPadding1, end = Dimens.SmallPadding1)
                ){
                    Image(
                        painterResource(R.drawable.park),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                        )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        "Парки",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Найкращі місця щоб провести час на свіжому повітрі",
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            item{
                Column (
                    modifier = Modifier.clickable { navigateToMuseums.invoke() }
                        .padding(start = Dimens.SmallPadding1, end = Dimens.SmallPadding1)
                ){
                    Image(
                        painterResource(R.drawable.museum),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                        )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        "Музеї",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Найдивовижніші місця щоб дізнатись щось нове",
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}