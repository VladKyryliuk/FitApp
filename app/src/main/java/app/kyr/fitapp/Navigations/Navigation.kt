package app.kyr.fitapp.Navigations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise
import app.kyr.fitapp.model.ProfileViewModel
import app.kyr.fitapp.screens.ExerciseDescription
import app.kyr.fitapp.screens.MyTraining
import app.kyr.fitapp.screens.Notification
import app.kyr.fitapp.screens.Profile
import app.kyr.fitapp.screens.Training
import app.kyr.fitapp.ui.theme.LightBlue
import app.kyr.fitapp.ui.theme.LightGrayPlus
import app.kyr.fitapp.ui.theme.Purple80
import kotlinx.coroutines.launch

//@Composable
//fun NavigationBar(currentTab: MutableState<Screens>) {
//    androidx.compose.material3.NavigationBar {
//        Screens.getAllScreens().forEach { tab ->
//            NavigationBarItem(
//                selected = tab == currentTab,
//                onClick = { currentTab.value = tab },
//                label = { Text(text = stringResource(id = tab.screen)) },
//                icon = {
//                    Icon(
//                        painter = painterResource(tab.icon),
//                        contentDescription = stringResource(id = tab.descriptionIcon)
//                    )
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = LightBlue,
//                    indicatorColor = LightGrayPlus,
//                    selectedTextColor = LightBlue
//                ),
//            )
//        }
//    }
//}

@Composable
fun BottomNavBar(countExercise: Int,currentTab: MutableState<Screens>) {
    val navigationController = rememberNavController()
    val profileViewModel = remember { ProfileViewModel() }
    val training: String = stringResource(id = Screens.Training.screen)
    val myTraining: String = stringResource(id = Screens.MyTraining.screen)
    val notification: String = stringResource(id = Screens.Notification.screen)
    val profile: String = stringResource(id = Screens.Profile.screen)
    val Exersice: String = stringResource(id = Screens.exercise.screen)
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.height(56.dp)
            ) {
                Screens.getAllScreens().forEach{
                        screens ->
                    var screen: String = stringResource(id = screens.screen)
                    IconButton(
                        onClick = {
                            currentTab.value = screens

                            navigationController.navigate(screen) {
                                popUpTo(0)
                            }
                            },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painterResource(
                                id = screens.icon
                            ),
                            contentDescription = stringResource(id = screens.descriptionIcon),
                            modifier = Modifier
                                .size(26.dp),
                            tint = if (currentTab.value == screens)
                                LightBlue else Color.LightGray
                        )
                    }

                }
            }

        }) { paddingValues ->

        NavHost(
            navController = navigationController,
            startDestination = stringResource(id = Screens.Training.screen),
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(training) { Training(countExercise,currentTab,navigationController) }
            composable(myTraining) { MyTraining(countExercise,currentTab,navigationController) }
            composable(notification) { Notification() }
            composable(profile) { Profile(profileViewModel) }
            composable(Exersice){ ExerciseDescription(currentTab,navigationController)}
        }
    }
}

@Composable
fun LeftNavBarWithText(currentTab: MutableState<Screens>) {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val training: String = stringResource(id = Screens.Training.screen)
    val myTraining: String = stringResource(id = Screens.MyTraining.screen)
    val notification: String = stringResource(id = Screens.Notification.screen)
    val profile: String = stringResource(id = Screens.Profile.screen)
    val Exersice: String = stringResource(id = Screens.exercise.screen)
    PermanentNavigationDrawer(
        drawerContent = {
            Column(modifier = Modifier
                .width(180.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primaryContainer)) {
                NavigationDrawerItem(
                    modifier = Modifier.padding(top = 50.dp),
                    label = { Text(text = "Training", color= LightBlue) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = Screens.Training.icon),
                            contentDescription = "Training", tint = LightBlue
                        )
                    },
                    onClick = {
                        navigationController.navigate("Training")
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor =  MaterialTheme.colorScheme.primaryContainer
                    )
                )
                NavigationDrawerItem(label = { Text(text = "My Training", color = LightBlue) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = Screens.MyTraining.icon),
                            contentDescription = "search", tint = LightBlue
                        )
                    },
                    onClick = {

                        coroutineScope.launch { drawerState.open() }

                        navigationController.navigate(myTraining)
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor =  MaterialTheme.colorScheme.primaryContainer
                    )

                )
                NavigationDrawerItem(label = { Text(text = "Profile", color = LightBlue) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = Screens.Profile.icon),
                            contentDescription = "profile", tint = LightBlue
                        )
                    },
                    onClick = {
                        coroutineScope.launch { drawerState.open() }
                        navigationController.navigate("Profile")
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        }, content = {
            val profileViewModel = remember { ProfileViewModel() }
            NavHost(
                navController = navigationController,
                startDestination = stringResource(id = Screens.Training.screen),

            ) {
                composable(training) { Training(4,currentTab,navigationController) }
                composable(myTraining) { MyTraining(4,currentTab,navigationController) }
                composable(notification) { Notification() }
                composable(profile) { Profile(profileViewModel) }
                composable(Exersice){ ExerciseDescription(currentTab,navigationController)}
            }
        }
    )
}

@Composable
fun LeftNavBar(currentTab: MutableState<Screens>) {
    val navigationController = rememberNavController()
    Surface(){
        val profileViewModel = remember { ProfileViewModel() }
        val notification: String = stringResource(id = Screens.Notification.screen)
        val Exersice: String = stringResource(id = Screens.exercise.screen)
        NavHost(
            navController = navigationController,
            startDestination = stringResource(id = Screens.Training.screen),
            modifier = Modifier.padding(start = 60.dp),
        ) {

            composable("Training") { Training(4,currentTab,navigationController) }
            composable("My Training") { MyTraining(4,currentTab,navigationController) }
            composable(notification) { Notification() }
            composable("Profile") { Profile(profileViewModel = profileViewModel) }
            composable(Exersice){ ExerciseDescription(currentTab,navigationController)}
        }
        NavigationRail(
            modifier = Modifier
                .width(60.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(top = 250.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            content = {
                NavigationRailItem(
                    modifier = Modifier.width(20.dp).padding(bottom = 20.dp),
                    icon = {
                        Icon(
                            painterResource(id = Screens.Training.icon),
                            contentDescription = stringResource(id = Screens.Training.descriptionIcon)
                        )
                    },
                    label = { },
                    selected = false,
                    onClick = {
                        navigationController.navigate("Training")
                    }
                )
                NavigationRailItem(
                    modifier = Modifier.width(20.dp).padding(bottom = 20.dp),
                    icon = {
                        Icon(
                            painterResource(id = Screens.MyTraining.icon),
                            contentDescription = stringResource(id = Screens.MyTraining.descriptionIcon)
                        )
                    },
                    label = { },
                    selected = false,
                    onClick = {
                        navigationController.navigate("My Training")
                    }
                )
                NavigationRailItem(
                    modifier = Modifier.width(20.dp),
                    icon = {
                        Icon(
                            painterResource(id = Screens.Profile.icon),
                            contentDescription = stringResource(id = Screens.Profile.descriptionIcon)
                        )
                    },
                    label = { },
                    selected = false,
                    onClick = {
                        navigationController.navigate("Profile")
                    }
                )

            })

    }


}