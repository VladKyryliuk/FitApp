package app.kyr.fitapp

import app.kyr.fitapp.data.Screens
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ScreensTest {

    @Test
    fun testGetScreen() {
        val screens = Screens.Training
        assertEquals(R.string.item_bar_training, screens.screen)
    }

    @Test
    fun testGetIcon() {
        val screens = Screens.Training
        assertEquals(R.drawable.dumbbell, screens.icon)
    }

    @Test
    fun testGetDescriptionIcon() {
        val screens = Screens.Training
        assertEquals(R.string.description_item_training, screens.descriptionIcon)
    }

    @Test
    fun testGetAllScreens() {
        val allScreens = Screens.getAllScreens()
        assertEquals(4, allScreens.size)
    }
}