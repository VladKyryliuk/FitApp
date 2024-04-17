package app.kyr.fitapp.app


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise
import app.kyr.fitapp.model.ProfileViewModel
import app.kyr.fitapp.screens.ExerciseDescription
import app.kyr.fitapp.screens.ExerciseDescriptionForMyTraining
import app.kyr.fitapp.screens.MyTraining
import app.kyr.fitapp.screens.Notification
import app.kyr.fitapp.screens.Profile
import app.kyr.fitapp.screens.Training
import app.kyr.fitapp.service.NavigationService
import app.kyr.fitapp.service.navigateBasedOnWindowSizeReturn


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitApp(windowSizeClass: WindowSizeClass) {

    var currentTab = remember {
        mutableStateOf<Screens>(Screens.Training)
    }
    var showMenu by remember {
        mutableStateOf(false)
    }

    var filter1 by remember {
        mutableStateOf("All")
    }

    var filter2 by remember {
        mutableStateOf("All")
    }

    var buttonBack = remember {
        mutableStateOf(false)
    }

    val blockNavForDescription = remember {
        mutableStateOf(false)
    }

    val navigationController = rememberNavController()

    val selectedExercise = remember {
        mutableStateOf<Exercise?>(null)
    }
    val selectedExercise2 = remember {
        mutableStateOf<Exercise?>(null)
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

//        TopBar
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = currentTab.value.screen),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            },

            navigationIcon = {
               val nav =  stringResource(id = currentTab.value.screen)
                if (buttonBack.value != false) {
                    IconButton(onClick = {
                        navigationController.navigate(nav)
                        buttonBack.value = false
                    }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            },

            actions = {
                val shouldShowFilterIcon =
                    (currentTab.value == Screens.Training || currentTab.value == Screens.MyTraining)
                            && !buttonBack.value
                if (shouldShowFilterIcon) {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Sharp.List,
                            contentDescription = "Item for Filter",
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .size(35.dp),
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier.width(70.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "All") },
                            onClick = {
                                if (currentTab.value == Screens.Training) {
                                    filter1 = "All"
                                }
                                if(currentTab.value == Screens.MyTraining) {
                                    filter2 = "All"
                                }

                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Hard") },
                            onClick = {
                                if (currentTab.value == Screens.Training) {
                                    filter1 = "Hard"
                                }
                                if(currentTab.value == Screens.MyTraining) {
                                    filter2 = "Hard"
                                }
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Middle") },
                            onClick = {
                                if (currentTab.value == Screens.Training) {
                                    filter1 = "Middle"
                                }
                                if(currentTab.value == Screens.MyTraining) {
                                    filter2 = "Middle"
                                }
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Easy") },
                            onClick = {
                                if (currentTab.value == Screens.Training) {
                                    filter1 = "Easy"
                                }
                                if(currentTab.value == Screens.MyTraining) {
                                    filter2 = "Easy"
                                }
                            }
                        )
                    }

                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

            NavigationService.navigateBasedOnWindowSize (
                navigationController,
                currentTab = currentTab,
                windowSize = windowSizeClass,
                navHostContent = {
                    NavHostForBottomAppBar(
                        modifier = Modifier.padding(0.dp),
                        navigationController = navigationController,
                        currentTab = currentTab,
                        windowSizeClass = windowSizeClass,
                        filter1 = filter1,
                        filter2 = filter2,
                        buttonBack = buttonBack,
                        blockNavForDescription = blockNavForDescription,
                        selectedExercise,
                        selectedExercise2
                    )
                },
                navHostContentForLeftNavBar = {
                    NavHostForLeftNavBarWithText(
                        modifier = Modifier.padding(0.dp),
                        navigationController = navigationController,
                        currentTab = currentTab,
                        windowSizeClass = windowSizeClass,
                        filter1 = filter1,
                        filter2 = filter2,
                        buttonBack = buttonBack,
                        blockNavForDescription = blockNavForDescription,
                        selectedExercise,
                        selectedExercise2
                    )
                }
            )
    }
}

@Composable
fun NavHostForBottomAppBar(
    modifier: Modifier,
    navigationController: NavController,
    currentTab: MutableState<Screens>,
    windowSizeClass: WindowSizeClass,
    filter1: String,
    filter2: String,
    buttonBack:MutableState<Boolean>,
    blockNavForDescription:MutableState<Boolean>,
    selectedExercise: MutableState<Exercise?>,
    selectedExercise2: MutableState<Exercise?>
) {
    val profileViewModel = remember { ProfileViewModel() }
    val training: String = stringResource(id = Screens.Training.screen)
    val myTraining: String = stringResource(id = Screens.MyTraining.screen)
    val notification: String = stringResource(id = Screens.Notification.screen)
    val profile: String = stringResource(id = Screens.Profile.screen)
    val Exersice: String = stringResource(id = Screens.exercise.screen)
    blockNavForDescription.value = true
    NavHost(
        modifier = modifier,
        navController = navigationController as NavHostController,
        startDestination = stringResource(id = Screens.Training.screen),
    ) {
        composable(training) {
            Training(
                filter1,
                countExercise = navigateBasedOnWindowSizeReturn(windowSizeClass),
                currentTab,
                navigationController,
                buttonBack,
                blockNavForDescription,
                selectedExercise,
                selectedExercise2
            )
        }
        composable(myTraining) {
            MyTraining(
                filter2,
                countExercise = navigateBasedOnWindowSizeReturn(windowSizeClass),
                currentTab,
                navigationController,
                buttonBack,
                blockNavForDescription,
                selectedExercise,
                selectedExercise2
            )
        }
        composable(notification) {
            Notification()
        }
        composable(profile) {
            Profile(
                profileViewModel
            )
        }
        composable(Exersice){
            ExerciseDescription(
                selectedExercise = selectedExercise.value,
            )
        }
    }
}

@Composable
fun NavHostForLeftNavBarWithText(
    modifier: Modifier,
    navigationController: NavController,
    currentTab: MutableState<Screens>,
    windowSizeClass: WindowSizeClass,
    filter1: String,
    filter2: String,
    buttonBack: MutableState<Boolean>,
    blockNavForDescription:MutableState<Boolean>,
    selectedExercise: MutableState<Exercise?>,
    selectedExercise2: MutableState<Exercise?>
) {
    val profileViewModel = remember { ProfileViewModel() }
    val training: String = stringResource(id = Screens.Training.screen)
    val myTraining: String = stringResource(id = Screens.MyTraining.screen)
    val notification: String = stringResource(id = Screens.Notification.screen)
    val profile: String = stringResource(id = Screens.Profile.screen)
    val Exersice: String = stringResource(id = Screens.exercise.screen)
    NavHost(
        modifier = modifier,
        navController = navigationController as NavHostController,
        startDestination = stringResource(id = Screens.Training.screen),
    ) {
        composable(training) {
            Row {
                Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Training(
                        filter1,
                        countExercise = navigateBasedOnWindowSizeReturn(windowSizeClass),
                        currentTab,
                        navigationController,
                        buttonBack,
                        blockNavForDescription,
                        selectedExercise,
                        selectedExercise2
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(1f)) {
                    ExerciseDescription(
                        selectedExercise = selectedExercise.value,
                    )
                }
            }
        }
        composable(myTraining) {
            Row{
                Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                    MyTraining(
                        filter2,
                        countExercise = navigateBasedOnWindowSizeReturn(windowSizeClass),
                        currentTab,
                        navigationController,
                        buttonBack,
                        blockNavForDescription,
                        selectedExercise,
                        selectedExercise2
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(1f)) {
                    ExerciseDescriptionForMyTraining(selectedExercise2 = selectedExercise2.value)

                }
            }
        }
        composable(notification) {
            Notification()
        }
        composable(profile) {
            Profile(
                profileViewModel
            )
        }
        composable(Exersice){
            ExerciseDescription(
                selectedExercise = selectedExercise.value,
            )
        }
    }
}
