package com.harmittaa.multipaltformcolors

import com.harmittaa.multipaltformcolors.repository.ColorRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(ColorRepository().greeting().contains("iOS"), "Check iOS is mentioned")
    }
}
