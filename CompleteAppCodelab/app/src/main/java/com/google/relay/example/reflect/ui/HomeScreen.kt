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

package com.google.relay.example.reflect.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.relay.example.reflect.model.*
import com.google.relay.example.reflect.ui.components.*

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEditTracker: (Tracker) -> Unit = {},
    onNewTracker: () -> Unit = {}
) {
    val data = ReflectRepo.model
    var day by remember { mutableStateOf(data.today()) }

    val deleteTracker: (Tracker) -> Unit = { tracker ->
        data.removeTracker(tracker)
    }

    Box(modifier.fillMaxSize()) {
        Column(modifier) {
            DayNavigationControl(
                modifier = Modifier.padding(vertical = 20.dp),
                onPrevTapped = {
                    day = data.getOrCreateDay(offsetDate(day.date, -1))
                },
                onNextTapped = {
                    day = data.getOrCreateDay(offsetDate(day.date, +1))
                },
            )

            day.trackerData.forEach {
                TrackerControl(
                    trackerData = it,
                    onEditTracker = onEditTracker,
                    onDeleteTracker = deleteTracker,
                    modifier = modifier.height(64.dp).padding(horizontal = 10.dp, vertical = 5.dp),
                )
            }
        }
        LargeFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset((-16).dp, (-16).dp),
            onClick = onNewTracker
        ) {
            Icon(
                Icons.Filled.Add,
                "Add Tracker",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}
