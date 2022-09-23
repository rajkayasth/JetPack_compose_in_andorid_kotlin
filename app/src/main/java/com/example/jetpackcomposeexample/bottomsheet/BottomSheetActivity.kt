@file:OptIn(ExperimentalMaterialApi::class)

package com.example.jetpackcomposeexample.bottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class BottomSheetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed,
            animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
                )

            val scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = sheetState
            )
            val scope = rememberCoroutineScope()
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Bottom Sheet",
                            fontSize = 50.sp
                        )

                    }
                },
                sheetBackgroundColor = Color.Green
            ) {
                Box(
                    modifier =
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        scope.launch {
                            if (sheetState.isCollapsed) {
                                sheetState.expand()
                            } else {
                                sheetState.collapse()
                            }

                        }
                    }) {
                        Text(text = "Bottom Sheet Fraction ${sheetState.progress.fraction}")
                    }
                }
            }
        }
    }
}