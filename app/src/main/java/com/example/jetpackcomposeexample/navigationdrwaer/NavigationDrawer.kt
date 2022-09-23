package com.example.jetpackcomposeexample.navigationdrwaer

import android.util.Log.d
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeexample.R

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.n2), contentDescription = "Header")
    }
}

@Composable
fun DrawerBody(
    item: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(item) { items ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(items)
                }
                .padding(16.dp)
            ) {
                Icon(imageVector = items.icon, contentDescription = items.contentDescription)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = items.title,
                    modifier = Modifier.weight(1f),
                    style = itemTextStyle
                )
            }
        }

    }
}