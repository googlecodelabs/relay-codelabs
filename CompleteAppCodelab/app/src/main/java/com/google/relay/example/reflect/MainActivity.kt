/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.relay.example.reflect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.relay.example.reflect.model.ReflectRepo
import com.google.relay.example.reflect.ui.HomeScreen
import com.google.relay.example.reflect.ui.SettingsScreen
import com.google.relay.example.reflect.ui.theme.ReflectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReflectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ReflectNavHost()
                }
            }
        }
    }
}

/*
 * Host for navigating between main screen and settings screen
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReflectNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = "home"
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            "home",
        ) {
            HomeScreen(
                onEditTracker = {
                    val index = ReflectRepo.model.getIndex(it)
                    navController.navigate("settings/$index")
                },
                onNewTracker =  {
                    navController.navigate("settings/-1")
                }
            )
        }
        composable(
            "settings/{trackerIndex}",
            arguments = listOf(navArgument("trackerIndex") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            },
        ) {
            val trackerIndex = it.arguments?.getInt("trackerIndex")
            val tracker = if (trackerIndex == null || trackerIndex == -1)
                null
            else
                ReflectRepo.model.getTracker(trackerIndex)
            SettingsScreen(tracker) {
                navController.navigate("home")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReflectTheme {
        HomeScreen()
    }
}