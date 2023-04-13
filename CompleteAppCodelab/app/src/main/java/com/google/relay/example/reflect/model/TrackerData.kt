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

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/*
 * The value of a particular tracker at a particular point in time.
 *
 * Note that the Tracker associated with this TrackerData is mutable, including its type. The
 * tracker type is therefore applied as a filter over the underlying value, so that - for example -
 * if a boolean tracker is changed to a count tracker, the count will start at 1.
 */
@Stable
class TrackerData(val tracker: Tracker) {
    private var _value by mutableStateOf(0)

    /*
     * The current value of this tracker, presented according to the current type.
     */
    var value: Int
        get() = when (tracker.type) {
            TrackerType.BOOLEAN -> if (_value > 0) 1 else 0
            TrackerType.COUNT -> if (_value < 0) 0 else _value
            TrackerType.RANGE ->
                if (_value < tracker.minValue)
                    tracker.minValue
                else if (_value > tracker.maxValue)
                    tracker.maxValue
                else
                    _value
        }
        set(newValue) {
            _value = newValue
        }

    /*
     * Convenience method for determining the toggle state of the data, assuming a boolean type.
     */
    fun isToggled(): Boolean = _value > 0

    /*
     * Convenience method for changing the toggle state of the data, assuming a boolean type.
     */
    fun toggle() {
        _value = if (_value > 0) 0 else 1
    }

    /*
     * Convenience method for incrementing the value of the data, assuming a count or range type.
     */
    fun increment() {
        _value++
    }

    /*
     * Convenience method for decrementing the value of the data, assuming a count or range type.
     */
    fun decrement() {
        _value--
    }

}
