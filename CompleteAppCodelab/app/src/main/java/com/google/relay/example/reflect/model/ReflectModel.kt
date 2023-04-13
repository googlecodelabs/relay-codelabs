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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import java.util.*

/*
 * Core data model for tracker information.
 *
 * ReflectModel ties together three pieces of information:
 * - Tracker contains information a user has configured for tracking
 * - TrackerData contains the specific value for a tracker on a given day
 * - Day contains a list of TrackerData values
 */
@Stable
class ReflectModel {
    private val trackers = mutableStateListOf<Tracker>()
    private val history = mutableStateListOf<Day>()

    fun today(): Day {
        if (history.size == 0) newDay(Date())
        return history.last()
    }

    fun newDay(date: Date): Day {
        val data = trackers.map { TrackerData(it) }
        val day = Day(date, data)
        history.add(day)
        return day
    }

    fun getOrCreateDay(date: Date): Day {
        return history.find { it.date == date } ?: newDay(date)
    }

    fun getIndex(tracker: Tracker): Int {
        return trackers.indexOf(tracker)
    }

    fun getTracker(index: Int): Tracker {
        return trackers[index]
    }

    fun addTracker(tracker: Tracker) {
        trackers.add(tracker)
        history.forEach { day ->
            day.addTracker(tracker)
        }
    }

    fun removeTracker(tracker: Tracker) {
        trackers.remove(tracker)
        history.forEach { day ->
            day.removeTracker(tracker)
        }
    }
}

@Stable
class Day(
    val date: Date,
    data: List<TrackerData>,
) {
    private val _trackerData = data.toMutableStateList()
    val trackerData: List<TrackerData>
        get() = _trackerData

    fun dataFor(tracker: Tracker): TrackerData? {
        return trackerData.find { it.tracker == tracker }
    }

    fun addTracker(tracker: Tracker) {
        _trackerData.add(TrackerData(tracker))
    }

    fun removeTracker(tracker: Tracker) {
        _trackerData.removeIf { it.tracker == tracker }
    }
}