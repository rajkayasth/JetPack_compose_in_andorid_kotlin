package com.example.jetpackcomposeexample.multilazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MultipleColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var items by remember {
                mutableStateOf(
                    (1..20).map {
                        ListItem(
                            title = "Item $it",
                            isSelect = false
                        )
                    }
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items.size) { i ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            items = items.mapIndexed { j, item ->
                                if (i == j) {
                                    item.copy(isSelect = !item.isSelect)
                                } else item
                            }
                        }
                        .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = items[i].title)
                        if (items[i].isSelect) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "selected",
                                tint = Color.Green,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                }

            }

        }
    }
}