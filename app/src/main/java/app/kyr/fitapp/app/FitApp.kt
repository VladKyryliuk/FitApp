package app.kyr.fitapp.app


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.kyr.fitapp.Navigations.BottomNavBar
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.ProfileViewModel
import app.kyr.fitapp.screens.ExerciseDescription
import app.kyr.fitapp.screens.MyTraining
import app.kyr.fitapp.screens.Notification
import app.kyr.fitapp.screens.Profile
import app.kyr.fitapp.screens.Training
import app.kyr.fitapp.service.NavigationService


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
    val navigationController = rememberNavController()




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
                if (currentTab.value == Screens.exercise) {
                    IconButton(onClick = {
                        navigationController.navigate("Training")
                    }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            },

            actions = {
                val shouldShowFilterIcon = currentTab.value == Screens.Training || currentTab.value == Screens.MyTraining
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


//        BottomBar
//            BottomNavBar(currentTab = currentTab)

        NavigationService.navigateBasedOnWindowSize(currentTab = currentTab, windowSize = windowSizeClass)

    }
}

