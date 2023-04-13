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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.relay.example.reflect.model.*
import com.google.relay.example.reflect.trackersettingsbar.TrackerSettingsBar
import com.google.relay.example.reflect.ui.components.*
import com.google.relay.example.reflect.R

/*
 * Default tracker if none is otherwise specified
 */
private fun defaultTracker(): Tracker {
    return Tracker("🍕", "Ate Pizza", TrackerType.BOOLEAN)
}

/*
 * Screen for editing a tracker's configuration.
 *
 * If the supplied tracker is null, the settings will be used to create a new tracker; otherwise
 * an existing tracker's configuration will be updated.
 */
@Composable
fun SettingsScreen(
    tracker: Tracker?,
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {}
) {
    val model = ReflectRepo.model
    var openBottomSheet by remember { mutableStateOf(false) }
    // Create a locally editable tracker object, which may or may not be applied to the
    // main data model depending on confirmation
    val localTracker by remember { mutableStateOf(tracker?.copy() ?: defaultTracker()) }
    val titleRes = if (tracker == null) R.string.tracker_new else R.string.tracker_edit
    val title = stringResource(titleRes)

    Column(
        modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        TrackerSettingsBar(
            title = title,
            saveLabel = stringResource(R.string.save_tracker),
            onCloseButtonTapped = {
                // Reject local tracker changes
                onClose()
            },
            onSaveButtonTapped = {
                // Accept local tracker changes
                if (tracker == null)
                    model.addTracker(localTracker)
                else {
                    tracker.configureAs(localTracker)
                }
                onClose()
            },
        )
        TrackerSettingsControl(
            tracker = localTracker,
            onEmojiFieldTapped = { openBottomSheet = !openBottomSheet }
        )
    }

    if (openBottomSheet) {
        EmojiSelector(
            onEmojiSelected = {
                localTracker.emoji = it
                openBottomSheet = false
            },
            onSheetClosed = {
                openBottomSheet = false
            }
        )
    }
}