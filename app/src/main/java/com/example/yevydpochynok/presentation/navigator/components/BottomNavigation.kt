package com.example.yevydpochynok.presentation.navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.yevydpochynok.presentation.Dimens.ExtraSmallPadding1
import com.example.yevydpochynok.presentation.Dimens.IconSize

@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
){
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.Gray
    ){
        items.forEachIndexed{ index,item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = {onItemClick(index)},
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize),
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding1))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
        }
    }

}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)