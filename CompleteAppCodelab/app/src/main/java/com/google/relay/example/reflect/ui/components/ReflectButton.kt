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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.relay.example.reflect.button.Configuration
import com.google.relay.example.reflect.button.State
import com.google.relay.example.reflect.button.materialIcons
import com.google.relay.example.reflect.ui.theme.ReflectTheme

@Composable
fun ReflectButton(
    modifier: Modifier = Modifier,
    configuration: Configuration = Configuration.Filled,
    state: State = State.Enabled,
    materialIconName: String = "",
    showIcon: Boolean = false,
    label: String = "",
    onTap: () -> Unit = {}
) {
    when (configuration) {
        Configuration.Tonal -> FilledTonalButton(
            onClick = onTap,
            modifier = modifier,
            enabled = state == State.Enabled,
        ) {
            if (showIcon) {
                Text(materialIconName, fontFamily = materialIcons)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            Text(label)
        }
        Configuration.Elevated -> ElevatedButton(
            onClick = onTap,
            modifier = modifier,
            enabled = state == State.Enabled,
        ) {
            if (showIcon) {
                Text(materialIconName, fontFamily = materialIcons)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            Text(label)
        }
        Configuration.Text -> TextButton(
            onClick = onTap,
            modifier = modifier,
            enabled = state == State.Enabled,
        ) {
            if (showIcon) {
                Text(materialIconName, fontFamily = materialIcons)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            Text(label)
        }
        Configuration.Outlined -> OutlinedButton(
            onClick = onTap,
            modifier = modifier,
            enabled = state == State.Enabled,
        ) {
            if (showIcon) {
                Text(materialIconName, fontFamily = materialIcons)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            Text(label)
        }
        Configuration.Filled -> Button(
            onClick = onTap,
            modifier = modifier,
            enabled = state == State.Enabled,
        ) {
            if (showIcon) {
                Text(materialIconName, fontFamily = materialIcons)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            Text(label)
        }
    }
}


@Preview
@Composable
private fun ButtonConfigurationTonalStateDisabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Disabled",
            configuration = Configuration.Tonal,
            state = State.Disabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTonalStatePressedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Pressed",
            configuration = Configuration.Tonal,
            state = State.Pressed
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTonalStateFocusedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Focused",
            configuration = Configuration.Tonal,
            state = State.Focused
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTonalStateHoveredPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Hovered",
            configuration = Configuration.Tonal,
            state = State.Hovered
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTonalStateEnabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Enabled",
            configuration = Configuration.Tonal,
            state = State.Enabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationElevatedStateDisabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Disabled",
            configuration = Configuration.Elevated,
            state = State.Disabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationElevatedStatePressedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Pressed",
            configuration = Configuration.Elevated,
            state = State.Pressed
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationElevatedStateFocusedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Focused",
            configuration = Configuration.Elevated,
            state = State.Focused
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationElevatedStateHoveredPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Hovered",
            configuration = Configuration.Elevated,
            state = State.Hovered
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationElevatedStateEnabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Enabled",
            configuration = Configuration.Elevated,
            state = State.Enabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTextStateDisabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Disabled",
            configuration = Configuration.Text,
            state = State.Disabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTextStatePressedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Pressed",
            configuration = Configuration.Text,
            state = State.Pressed
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTextStateFocusedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Focused",
            configuration = Configuration.Text,
            state = State.Focused
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTextStateHoveredPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Hovered",
            configuration = Configuration.Text,
            state = State.Hovered
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationTextStateEnabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Enabled",
            configuration = Configuration.Text,
            state = State.Enabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationOutlinedStateDisabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Disabled",
            configuration = Configuration.Outlined,
            state = State.Disabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationOutlinedStatePressedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Pressed",
            configuration = Configuration.Outlined,
            state = State.Pressed
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationOutlinedStateFocusedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Focused",
            configuration = Configuration.Outlined,
            state = State.Focused
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationOutlinedStateHoveredPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Hovered",
            configuration = Configuration.Outlined,
            state = State.Hovered
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationOutlinedStateEnabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Enabled",
            configuration = Configuration.Outlined,
            state = State.Enabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationFilledStateDisabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Disabled",
            configuration = Configuration.Filled,
            state = State.Disabled
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationFilledStatePressedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Pressed",
            configuration = Configuration.Filled,
            state = State.Pressed
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationFilledStateFocusedPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Focused",
            configuration = Configuration.Filled,
            state = State.Focused
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationFilledStateHoveredPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Hovered",
            configuration = Configuration.Filled,
            state = State.Hovered
        )
    }
}

@Preview
@Composable
private fun ButtonConfigurationFilledStateEnabledPreview() {
    ReflectTheme {
        ReflectButton(
            onTap = {},
            materialIconName = "add",
            showIcon = true,
            label = "Enabled",
            configuration = Configuration.Filled,
            state = State.Enabled
        )
    }
}
