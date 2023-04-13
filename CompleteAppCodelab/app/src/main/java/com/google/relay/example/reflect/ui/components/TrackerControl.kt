package com.google.relay.example.reflect.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.relay.example.reflect.model.Tracker
import com.google.relay.example.reflect.model.TrackerData
import com.google.relay.example.reflect.model.TrackerType

/*
 * A component for controlling trackers.
 *
 * TrackerControl is responsible for providing interaction and state management common to all
 * tracker types, by providing a context menu with edit and delete controls.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackerControl(
    trackerData: TrackerData,
    modifier: Modifier = Modifier,
    onEditTracker: (Tracker) -> Unit = {},
    onDeleteTracker: (Tracker) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var confirming by remember { mutableStateOf(false) }

    Box(modifier = modifier) {

        // TODO: replace with Relay tracker components
        Text(text = trackerData.tracker.toString())

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = { onEditTracker(trackerData.tracker) },
                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = { confirming = true },
                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
            )
        }
    }

    if (confirming) {
        AlertDialog(
            onDismissRequest = { confirming = false },
            title = {
                Text(text = "Remove tracker?")
            },
            text = {
                Text("All historical data will also be removed.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        confirming = false
                        onDeleteTracker(trackerData.tracker)
                    }
                ) {
                    Text("Remove")
                }
            },
            dismissButton = {
                Button(
                    onClick = { confirming = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}