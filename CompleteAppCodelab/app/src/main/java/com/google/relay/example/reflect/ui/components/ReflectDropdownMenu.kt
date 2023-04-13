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

package com.google.relay.example.reflect.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.relay.example.reflect.textfield.DesignSelection
import com.google.relay.example.reflect.textfield.State
import com.google.relay.example.reflect.textfield.Style
import com.google.relay.example.reflect.textfield.TextConfigurations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReflectDropdownMenu(
    modifier: Modifier = Modifier,
    items: String = "",
    selectedIndex: Double = 0.0,
    labelText: String = "",
    onChange: (Double) -> Unit = {},
    textFieldDesign: DesignSelection = DesignSelection(
        style = Style.Outlined,
        state = State.Enabled,
        textConfigurations = TextConfigurations.InputText
    ),
) {
    val options = items.split(',')

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[selectedIndex.toInt()]) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        when (textFieldDesign.style) {
            Style.Outlined -> OutlinedTextField(
                value = selectedOptionText,
                onValueChange = {},
                readOnly = true,
                label = { Text(labelText) },
                enabled = textFieldDesign.state == State.Enabled,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                ),
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier.menuAnchor(),
            )

            Style.Filled -> TextField(
                value = selectedOptionText,
                onValueChange = {},
                readOnly = true,
                label = { Text(labelText) },
                enabled = textFieldDesign.state == State.Enabled,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                ),
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier.menuAnchor(),
            )

        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEachIndexed { index, selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onChange(index.toDouble())
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
