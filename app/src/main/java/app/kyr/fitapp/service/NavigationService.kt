package app.kyr.fitapp.service

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import app.kyr.fitapp.Navigations.BottomNavBar
import app.kyr.fitapp.Navigations.LeftNavBar
import app.kyr.fitapp.Navigations.LeftNavBarWithText
import app.kyr.fitapp.data.Screens

object NavigationService {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    fun navigateBasedOnWindowSize(
        navController: NavController,
        currentTab: MutableState<Screens>,
        windowSize: WindowSizeClass,
        navHostContent: @Composable (NavController) -> Unit,
        navHostContentForLeftNavBar: @Composable (NavController) -> Unit
    ) {

        when {
            windowSize.heightSizeClass == WindowHeightSizeClass.Compact -> {
                BottomNavBar(navController, currentTab, navHostContent)
            }
            windowSize.widthSizeClass == WindowWidthSizeClass.Compact -> {
                BottomNavBar(navController,currentTab,navHostContent)
            }
            windowSize.widthSizeClass == WindowWidthSizeClass.Medium -> {
                when (windowSize.heightSizeClass) {
                    WindowHeightSizeClass.Medium -> LeftNavBar(navController,currentTab,navHostContent)
                    WindowHeightSizeClass.Expanded -> LeftNavBar(navController,currentTab,navHostContent)
                    else -> {}
                }
            }
            windowSize.widthSizeClass == WindowWidthSizeClass.Expanded -> {
                when (windowSize.heightSizeClass) {
                    WindowHeightSizeClass.Medium -> LeftNavBarWithText(navController,currentTab,navHostContentForLeftNavBar)
                    WindowHeightSizeClass.Expanded -> LeftNavBarWithText(navController,currentTab,navHostContentForLeftNavBar)
                    else -> {}
                }
            }
            else -> {
            }
        }
    }
}

@Composable
fun navigateBasedOnWindowSizeReturn(windowSize: WindowSizeClass?): Int {
if (windowSize != null) {
    return when {
        windowSize.heightSizeClass == WindowHeightSizeClass.Compact -> {
            3
        }

        windowSize.widthSizeClass == WindowWidthSizeClass.Compact -> {
            2
        }

        windowSize.widthSizeClass == WindowWidthSizeClass.Medium -> {
            when (windowSize.heightSizeClass) {
                WindowHeightSizeClass.Medium -> 3
                WindowHeightSizeClass.Expanded -> 3
                else -> 0
            }
        }

        windowSize.widthSizeClass == WindowWidthSizeClass.Expanded -> {
            when (windowSize.heightSizeClass) {
                WindowHeightSizeClass.Medium -> 2
                WindowHeightSizeClass.Expanded -> 2
                else -> 0
            }
        }

        else -> {
            0
        }
    }
}
    else {
        return 2
    }
}