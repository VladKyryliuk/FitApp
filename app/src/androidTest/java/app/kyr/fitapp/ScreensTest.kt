package app.kyr.fitapp

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.kyr.fitapp.app.FitApp
import app.kyr.fitapp.app.NavHostForBottomAppBar
import app.kyr.fitapp.data.AllTraining
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise
import app.kyr.fitapp.model.ProfileViewModel
import app.kyr.fitapp.screens.MyTraining
import app.kyr.fitapp.screens.Profile
import app.kyr.fitapp.screens.Training
import app.kyr.fitapp.service.ProductService
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ScreensTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController



    @Test
    fun should_render_ui_with_name_age_and_bio_fields() {
        // Arrange
        val profileViewModel = ProfileViewModel()

        // Act
        composeTestRule.setContent {
            Profile(profileViewModel = profileViewModel)
        }

        // Assert
        composeTestRule.onNodeWithText("Name:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Age:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bio:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cancel").assertIsDisplayed()
        composeTestRule.onNodeWithText("Save").assertIsDisplayed()
    }

    @Test
    fun should_allow_user_to_input_data_in_name_age_and_bio_fields() {
        // Arrange
        val profileViewModel = ProfileViewModel()

        // Act
        composeTestRule.setContent {
            Profile(profileViewModel = profileViewModel)
        }

        // Assert
        composeTestRule.onNodeWithText("Your Name").performTextInput("John Doe")
        composeTestRule.onNodeWithText("Your Age").performTextInput("25")
        composeTestRule.onNodeWithText("default bio").performTextInput("Lorem ipsum dolor sit amet")
        composeTestRule.onNodeWithText("Save").performClick()
        assertEquals("John Doe", profileViewModel.name)
        assertEquals("25", profileViewModel.age)
        assertEquals("Lorem ipsum dolor sit amet", profileViewModel.bio)
    }

    @Test
    fun should_display_vertical_grid_with_exercises() {
        // Arrange
        val navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)
        val countExercise = 2
        val currentTab = mutableStateOf<Screens>(Screens.Training)
        val filter1 = "Hard"
        val buttonBack = mutableStateOf(false)
        val blockNavForDescription = mutableStateOf(true)
        val selectedExercise = mutableStateOf<Exercise?>(null)
        val selectedExercise2 = mutableStateOf<Exercise?>(null)
        var isLoading = mutableStateOf(false)

        // Act
        composeTestRule.setContent {
            Training(
                filter1 = filter1,
                countExercise = countExercise,
                currentTab = currentTab,
                navController = navController,
                buttonBack = buttonBack,
                blockNavForDescription = blockNavForDescription,
                selectedExercise = selectedExercise,
                selectedExercise2 = selectedExercise2,
                AllTraining().loadFitsInfo(),
                isLoading
            )
        }

        // Assert
        composeTestRule.onNodeWithText("Віджимання").assertIsDisplayed()
        composeTestRule.onNodeWithText("Розтяжка").assertIsDisplayed()
    }

    @Test
    fun should_display_no_exercises_message_for_my_training_screen() {
        // Arrange
        val navController = TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)
        val exerciseList = emptyList<Exercise>()
        val countExercise = 2
        val currentTab = mutableStateOf<Screens>(Screens.MyTraining)
        val filter2 = "All"
        val buttonBack = mutableStateOf(false)
        val blockNavForDescription = mutableStateOf(true)
        val selectedExercise = mutableStateOf<Exercise?>(null)
        val selectedExercise2 = mutableStateOf<Exercise?>(null)

        // Act
        composeTestRule.setContent {
            MyTraining(
                filter2 = filter2,
                countExercise = countExercise,
                currentTab = currentTab,
                navController = navController,
                buttonBack = buttonBack,
                blockNavForDescription = blockNavForDescription,
                selectedExercise = selectedExercise,
                selectedExercise2 = selectedExercise2,
                exerciseList
            )
        }

        // Assert
        composeTestRule.onNodeWithText("You haven't added any exercise").assertIsDisplayed()
    }

    @Test
    fun should_display_added_exercises_for_my_training_screen() {
        // Arrange
        ProductService.addExercises(Exercise(3,R.drawable.running, R.string.exercise_run, R.string.level_medium))
        ProductService.addExercises(Exercise(6,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium))

        val navController =
            TestNavHostController(InstrumentationRegistry.getInstrumentation().targetContext)
        val countExercise = 2
        val currentTab = mutableStateOf<Screens>(Screens.MyTraining)
        val filter2 = "All"
        val buttonBack = mutableStateOf(false)
        val blockNavForDescription = mutableStateOf(true)
        val selectedExercise = mutableStateOf<Exercise?>(null)
        val selectedExercise2 = mutableStateOf<Exercise?>(null)

        // Act
        composeTestRule.setContent {
            MyTraining(
                filter2 = filter2,
                countExercise = countExercise,
                currentTab = currentTab,
                navController = navController,
                buttonBack = buttonBack,
                blockNavForDescription = blockNavForDescription,
                selectedExercise = selectedExercise,
                selectedExercise2 = selectedExercise2,
                ProductService.getListExercises()
            )
        }

        // Assert
        composeTestRule.onNodeWithText("Присідання").assertIsDisplayed()
        composeTestRule.onNodeWithText("Біг").assertIsDisplayed()
    }


    @Test
    fun testNavigateToExerciseDescriptionScreen(){

        val exerciseList = AllTraining().loadFitsInfo()

        val currentTab = mutableStateOf<Screens>(Screens.Training)
        val buttonBack = mutableStateOf(false)
        val blockNavForDescription = mutableStateOf(true)
        val selectedExercise = mutableStateOf<Exercise?>(null)
        val selectedExercise2 = mutableStateOf<Exercise?>(null)
        var isLoading = mutableStateOf(false)

        // Act
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            NavHostForBottomAppBar(
                modifier = Modifier.padding(0.dp),
                navigationController = navController,
                currentTab = currentTab,
                windowSizeClass = null,
                filter1 = "All",
                filter2 = "All",
                buttonBack = buttonBack,
                blockNavForDescription = blockNavForDescription,
                selectedExercise,
                selectedExercise2,
                isLoading
            )

        }

        exerciseList.forEach { exercise ->
            val titleText = composeTestRule.activity.getString(exercise.descriptionId)
            composeTestRule.onNodeWithText(titleText).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(text = "Присідання").performClick()

        Thread.sleep(5000)

        val expectedRoute = "Exercise"

        Assert.assertEquals(navController.currentDestination?.route, expectedRoute)

        val currentDestination = navController.currentDestination

        assert(currentDestination?.route == expectedRoute)

    }


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Test
    fun testOrientationBottomNavBar() {

        val windowSizeClass = WindowSizeClass.calculateFromSize(size = DpSize(500.dp,600.dp)
        )

        composeTestRule.setContent {
            FitApp(windowSizeClass = windowSizeClass)
        }

        composeTestRule.onNodeWithTag("BottomNavBar").assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Test
    fun testOrientationLeftNavBar() {

        val windowSizeClass = WindowSizeClass.calculateFromSize(size = DpSize(800.dp,600.dp)
        )

        composeTestRule.setContent {
            FitApp(windowSizeClass = windowSizeClass)
        }

        composeTestRule.onNodeWithTag("LeftNavBar").assertIsDisplayed()
    }
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Test
    fun testOrientationLeftNavBarWithText() {

        val windowSizeClass = WindowSizeClass.calculateFromSize(size = DpSize(1200.dp,1000.dp)
        )

        composeTestRule.setContent {
            FitApp(windowSizeClass = windowSizeClass)
        }

        composeTestRule.onNodeWithTag("LeftNavBarWithText").assertIsDisplayed()
    }

}