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

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.relay.example.reflect.textfield.State
import com.google.relay.example.reflect.textfield.Style
import com.google.relay.example.reflect.textfield.TextConfigurations
import com.google.relay.example.reflect.textfield.materialIcons
import com.google.relay.example.reflect.ui.theme.ReflectTheme

/*
 * Wrapper for a Material 3 TextField suitable for reference by a Relay component.
 *
 * ReflectTextField conforms to the interface defined by the `text_field` UI package, which in turn
 * is defined by the Figma "Text Field" component. The Text Field Figma component used here is a
 * slightly modified version of the Material 3 Design Kit component Text Field. Designers using
 * this Text Field component can supply configuration, such as style and choice of icon, which are
 * carried through in generated code to this component and thus to the underlying [TextField] or
 * [OutlinedTextField].
 */
@Composable
fun ReflectTextField(
    modifier: Modifier = Modifier,
    style: Style = Style.Outlined,
    state: State = State.Enabled,
    // textConfigurations is unused. Instead, M3 TextField determines its text configuration
    // by whether labelText and placeholderText are not empty.
    @Suppress("UNUSED_PARAMETER") textConfigurations: TextConfigurations = TextConfigurations.InputText,
    inputText: String = "",
    labelText: String = "",
    placeholderText: String = "",
    showSupportingText: Boolean = false,
    supportingText: String = "",
    showLeadingIcon: Boolean = false,
    leadingIcon: String = "search",
    showTrailingIcon: Boolean = false,
    trailingIcon: String = "cancel",
    onChange: (String) -> Unit = {}
) {
    val leadingIconText: @Composable (() -> Unit)? = if (showLeadingIcon) {
        {
            Text(leadingIcon, fontFamily = materialIcons)
        }
    } else null
    val trailingIconText: @Composable (() -> Unit)? = if (showTrailingIcon) {
        {
            Text(trailingIcon, fontFamily = materialIcons)
        }
    } else null

    when (style) {
        Style.Filled -> TextField(modifier = modifier,
            value = inputText,
            onValueChange = onChange,
            enabled = state != State.Disabled,
            label = { if (labelText.isNotEmpty()) Text(labelText) },
            placeholder = { if (placeholderText.isNotEmpty()) Text(placeholderText) },
            supportingText = { if (showSupportingText) Text(supportingText) },
            isError = state == State.Error,
            leadingIcon = leadingIconText,
            trailingIcon = trailingIconText,
        )
        Style.Outlined -> OutlinedTextField(modifier = modifier,
            value = inputText,
            onValueChange = onChange,
            enabled = state != State.Disabled,
            label = { if (labelText.isNotEmpty()) Text(labelText) },
            placeholder = { if (placeholderText.isNotEmpty()) Text(placeholderText) },
            supportingText = { if (showSupportingText) Text(supportingText) },
            isError = state == State.Error,
            leadingIcon = leadingIconText,
            trailingIcon = trailingIconText,
        )
    }
}

/*
 * The rest of this file consists of previews for ReflectTextField in various configurations.
 * NOTE: There are no previews for placeholder text, because the text field needs focus
 * to show placeholder text, and it's not clear how to preview a composable with focus; see
 * https://stackoverflow.com/questions/75074398/focus-state-in-jetpacks-compose-previews.
 */

@Preview(showBackground = true)
@Composable
private fun Outlined_Disabled_LabelTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Outlined,
            state = State.Disabled,
            textConfigurations = TextConfigurations.LabelText,
            inputText = "",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Outlined_Error_LabelTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Outlined,
            state = State.Error,
            textConfigurations = TextConfigurations.LabelText,
            inputText = "",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            trailingIcon = "error",
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Outlined_Enabled_LabelTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Outlined,
            state = State.Enabled,
            textConfigurations = TextConfigurations.LabelText,
            inputText = "",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Outlined_Disabled_InputTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Outlined,
            state = State.Disabled,
            textConfigurations = TextConfigurations.InputText,
            inputText = "Input",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Outlined_Error_InputTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Outlined,
            state = State.Error,
            textConfigurations = TextConfigurations.InputText,
            inputText = "Input",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Outlined_Enabled_InputTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Outlined,
            state = State.Enabled,
            textConfigurations = TextConfigurations.InputText,
            inputText = "Input",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Filled_Disabled_LabelTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Filled,
            state = State.Disabled,
            textConfigurations = TextConfigurations.LabelText,
            inputText = "",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Filled_Error_LabelTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Filled,
            state = State.Error,
            textConfigurations = TextConfigurations.LabelText,
            inputText = "",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            trailingIcon = "error",
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Filled_Enabled_LabelTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Filled,
            state = State.Enabled,
            textConfigurations = TextConfigurations.LabelText,
            inputText = "",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Filled_Disabled_InputTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Filled,
            state = State.Disabled,
            textConfigurations = TextConfigurations.InputText,
            inputText = "Input",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Filled_Error_InputTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Filled,
            state = State.Error,
            textConfigurations = TextConfigurations.InputText,
            inputText = "Input",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Filled_Enabled_InputTextPreview() {
    ReflectTheme {
        ReflectTextField(
            style = Style.Filled,
            state = State.Enabled,
            textConfigurations = TextConfigurations.InputText,
            inputText = "Input",
            labelText = "Label",
            showLeadingIcon = true,
            showTrailingIcon = true,
            showSupportingText = true,
            supportingText = "Supporting text",
        )
    }
}
