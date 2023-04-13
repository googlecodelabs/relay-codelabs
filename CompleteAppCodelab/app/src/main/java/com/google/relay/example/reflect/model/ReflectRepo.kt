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

/*
 * A fake repository for model data.
 */
object ReflectRepo {
    val model = createInitialModel()
}

private fun createInitialModel(): ReflectModel {
    val model = ReflectModel()

    val callMom = Tracker(
        emoji = "📞",
        name = "Call Mom",
        type = TrackerType.BOOLEAN,
    )

    val sleepQuality = Tracker(
        emoji = "😴",
        name = "Sleep quality",
        type = TrackerType.RANGE,
        minValue = 0,
        maxValue = 10,
    )

    val mood = Tracker(
        emoji = "😊",
        name = "Mood",
        type = TrackerType.RANGE,
        minValue = 0,
        maxValue = 5,
    )

    val drankWater = Tracker(
        emoji = "💧",
        name = "Drank water",
        type = TrackerType.COUNT,
    )

    model.apply {
        addTracker(callMom)
        addTracker(sleepQuality)
        addTracker(mood)
        addTracker(drankWater)
    }

    model.today().apply {
        dataFor(callMom)?.value = 1
        dataFor(sleepQuality)?.value = 7
        dataFor(mood)?.value = 7
        dataFor(drankWater)?.increment()
        dataFor(drankWater)?.increment()
        dataFor(drankWater)?.increment()
    }

    return model
}
