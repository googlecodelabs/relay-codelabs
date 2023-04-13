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

package com.google.relay.example.reflect.model

import androidx.compose.runtime.*

/*
 * The type of values the tracker will track over time.
 */
enum class TrackerType {
    BOOLEAN,
    RANGE,
    COUNT
}

/*
 * The settings for a particular tracker.
 *
 * Note that all properties, including type, are user-configurable.
 */
@Stable
class Tracker(
    emoji: String,
    name: String,
    type: TrackerType,
    units: String = "",
    minValue: Int = 0,
    maxValue: Int = 5,
    step: Int = 1,
) {
    var emoji by mutableStateOf(emoji)
    var name by mutableStateOf(name)
    var type by mutableStateOf(type)
    var units by mutableStateOf(units)
    var minValue by mutableStateOf(minValue)
    var maxValue by mutableStateOf(maxValue)
    var step by mutableStateOf(step)

    fun copy(): Tracker = Tracker(
        emoji,
        name,
        type,
        units,
        minValue,
        maxValue,
        step
    )

    fun configureAs(other: Tracker) {
        emoji = other.emoji
        name = other.name
        type = other.type
        units = other.units
        minValue = other.minValue
        maxValue = other.maxValue
        step = other.step
    }

    override fun toString(): String {
        return "{ type: $type, name: $name, emoji: $emoji }"
    }
}
