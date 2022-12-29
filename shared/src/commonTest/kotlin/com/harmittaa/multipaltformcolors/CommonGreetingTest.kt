package com.harmittaa.multipaltformcolors

import com.harmittaa.multipaltformcolors.repository.ColorRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(ColorRepository().greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }
}
