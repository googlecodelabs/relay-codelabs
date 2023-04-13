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

package com.google.relay.example.reflect.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val ReflectDarkColorScheme = darkColorScheme(
    background = Tan20,
    onBackground = Tan89,
    surface = Tan10,
    onSurface = Tan89,
    surfaceVariant = Orange60,
    onSurfaceVariant = Tan89,
    primary = Orange95,
    onPrimary = White,
    primaryContainer = Orange95,
    onPrimaryContainer = Orange40,
    secondaryContainer = Tan30,
    onSecondaryContainer = Tan89,
    outline = Tan70
)

private val ReflectLightColorScheme = lightColorScheme(
    background = Tan80,
    onBackground = Tan29,
    surface = Tan99,
    onSurface = Tan50,
    surfaceVariant = Tan95,
    onSurfaceVariant = Tan50,
    primary = Orange70,
    onPrimary = White,
    primaryContainer = Orange99,
    onPrimaryContainer = Tan50,
    secondaryContainer = Tan90,
    onSecondaryContainer = Tan50,
    outline = Tan65
)

@Composable
fun ReflectTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val reflectColorScheme = when {
        isDarkTheme -> ReflectDarkColorScheme
        else -> ReflectLightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = reflectColorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = reflectColorScheme,
        typography = ReflectTypography,
        shapes = Shapes,
        content = content
    )
}