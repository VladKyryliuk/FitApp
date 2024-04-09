package app.kyr.fitapp.Navigations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.ui.theme.LightBlue
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
fun BottomNavBar(navController: NavController,currentTab: MutableState<Screens>,
                 navHostContent: @Composable (NavController) -> Unit) {

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.height(56.dp)
            ) {
                Screens.getAllScreens().forEach{
                        screens ->
                    val screen: String = stringResource(id = screens.screen)
                    IconButton(
                        onClick = {
                            currentTab.value = screens

                            navController.navigate(screen) {
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
            Box(Modifier.padding(paddingValues)) {
                navHostContent(navController)
            }
        }

    }

@Composable
fun LeftNavBarWithText(
    navController: NavController, currentTab: MutableState<Screens>,
    navHostContent: @Composable (NavController) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val myTraining: String = stringResource(id = Screens.MyTraining.screen)
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
                            navController.navigate("Training")
                            currentTab.value = Screens.Training
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
                            navController.navigate(myTraining)
                            currentTab.value = Screens.MyTraining
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
                            navController.navigate("Profile")
                            currentTab.value = Screens.Profile
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }
            },
            content = {
                navHostContent(navController)
            }
        )
}




@Composable
fun LeftNavBar(navController: NavController,currentTab: MutableState<Screens>,
               navHostContent: @Composable (NavController) -> Unit) {
    Surface{

        navHostContent(navController)
        NavigationRail(
            modifier = Modifier
                .width(60.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(top = 250.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            content = {
                NavigationRailItem(
                    modifier = Modifier
                        .width(20.dp)
                        .padding(bottom = 20.dp),
                    icon = {
                        Icon(
                            painterResource(id = Screens.Training.icon),
                            contentDescription = stringResource(id = Screens.Training.descriptionIcon)
                        )
                    },
                    label = { },
                    selected = false,
                    onClick = {
                        navController.navigate("Training")
                        currentTab.value = Screens.Training
                    }
                )
                NavigationRailItem(
                    modifier = Modifier
                        .width(20.dp)
                        .padding(bottom = 20.dp),
                    icon = {
                        Icon(
                            painterResource(id = Screens.MyTraining.icon),
                            contentDescription = stringResource(id = Screens.MyTraining.descriptionIcon)
                        )
                    },
                    label = { },
                    selected = false,
                    onClick = {
                        navController.navigate("My Training")
                        currentTab.value = Screens.MyTraining
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
                        navController.navigate("Profile")
                        currentTab.value = Screens.Profile
                    }
                )

            })

    }


}