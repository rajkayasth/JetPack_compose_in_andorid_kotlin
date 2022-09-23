@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.jetpackcomposeexample.permissiondemo

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale


class PermissionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val permissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.CAMERA
                )
            )

            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(
                key1 = lifecycleOwner,
                effect = {
                    val observer = LifecycleEventObserver{_,event->
                        if (event== Lifecycle.Event.ON_START){
                            permissionState.launchMultiplePermissionRequest()
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)

                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                permissionState.permissions.forEach { perm ->
                    when (perm.permission) {
                        Manifest.permission.CAMERA -> {
                            when {
                                perm.status.shouldShowRationale -> {
                                    Text(text = " Permission is needed to access to camera ")

                                }
                                perm.status.isGranted -> {
                                    Text(text = "Camera Permission Accepted ")

                                }
                                perm.isPermanentlyDenied() -> {
                                    Text(text = "Camera permission is Permanently denied u can enable it from settings")

                                }
                            }
                        }
                        Manifest.permission.RECORD_AUDIO -> {
                            when {
                                perm.status.shouldShowRationale -> {
                                    Text(text = " Permission is needed to access to Audio ")

                                }
                                perm.status.isGranted -> {
                                    Text(text = "Audio Permission Accepted ")

                                }
                                perm.isPermanentlyDenied() -> {
                                    Text(text = "Audio permission is Permanently denied u can enable it from settings")

                                }
                            }
                        }
                    }
                }


            }
        }
    }
}