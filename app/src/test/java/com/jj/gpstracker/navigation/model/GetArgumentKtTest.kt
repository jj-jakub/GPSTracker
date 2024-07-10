package com.jj.gpstracker.navigation.model

import androidx.lifecycle.SavedStateHandle
import com.jj.gpstracker.framework.navigation.model.Route
import com.jj.gpstracker.framework.navigation.model.getArgument
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import kotlin.reflect.KProperty1

class GetArgumentKtTest {

    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    @Test
    fun `getArgument should return value when present`() {
        val property: KProperty1<Route.SecondaryScreen, String> = Route.SecondaryScreen::text
        val expectedValue = "testText"

        every { savedStateHandle.get<String>(property.name) } returns expectedValue

        val result = savedStateHandle.getArgument(property)

        assertEquals(expectedValue, result)
    }

    @Test
    fun `getArgument should return null when value is not present`() {
        val property: KProperty1<Route.SecondaryScreen, String> = Route.SecondaryScreen::text

        every { savedStateHandle.get<String>(property.name) } returns null

        val result = savedStateHandle.getArgument(property)

        assertNull(result)
    }
}
