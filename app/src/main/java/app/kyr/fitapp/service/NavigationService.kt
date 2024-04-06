package app.kyr.fitapp.service

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import app.kyr.fitapp.Navigations.BottomNavBar
import app.kyr.fitapp.Navigations.LeftNavBar
import app.kyr.fitapp.Navigations.LeftNavBarWithText
import app.kyr.fitapp.data.Screens

object NavigationService {

    @Composable
    fun navigateBasedOnWindowSize(currentTab: MutableState<Screens>, windowSize: WindowSizeClass) {

        when {
            windowSize.heightSizeClass == WindowHeightSizeClass.Compact -> {
                BottomNavBar(3, currentTab)
            }
            windowSize.widthSizeClass == WindowWidthSizeClass.Compact -> {
                BottomNavBar(2, currentTab)
            }
            windowSize.widthSizeClass == WindowWidthSizeClass.Medium -> {
                when (windowSize.heightSizeClass) {
                    WindowHeightSizeClass.Medium -> LeftNavBar(currentTab)
                    WindowHeightSizeClass.Expanded -> LeftNavBar(currentTab)
                    else -> {}
                }
            }
            windowSize.widthSizeClass == WindowWidthSizeClass.Expanded -> {
                when (windowSize.heightSizeClass) {
                    WindowHeightSizeClass.Medium -> LeftNavBarWithText(currentTab)
                    WindowHeightSizeClass.Expanded -> LeftNavBarWithText(currentTab)
                    else -> {}
                }
            }
            else -> {
            }
        }
    }
}