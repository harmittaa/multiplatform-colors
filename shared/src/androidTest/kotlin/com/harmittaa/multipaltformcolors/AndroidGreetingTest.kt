package com.harmittaa.multipaltformcolors

import com.harmittaa.multipaltformcolors.repository.ColorRepository
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", ColorRepository().greeting().contains("Android"))
    }
}
