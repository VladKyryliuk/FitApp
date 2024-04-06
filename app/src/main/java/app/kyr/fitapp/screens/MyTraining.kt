package app.kyr.fitapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.kyr.fitapp.component.FitVerticalGrid
import app.kyr.fitapp.data.AllTraining
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.service.ProductService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTraining(countExercise:Int,currentTab:MutableState<Screens>,navController: NavController) {
    var showMenu by remember {
        mutableStateOf(false)
    }

    var filter by remember {
        mutableStateOf("All")
    }

    Column {
//        TopBar
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = Screens.MyTraining.screen),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                },


                actions = {

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
                                filter = "All"
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Hard") },
                            onClick = {
                                filter = "Hard"
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Middle") },
                            onClick = {
                                filter = "Middle"
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Easy") },
                            onClick = {
                                filter = "Easy"
                            }
                        )
                    }


                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                FitVerticalGrid(
                    countExercise,
                    currentTab,
                    navController,
                    exerciseList = ProductService.getListExercises(),
                    selectedFilter = filter
                )

            }
        }
    }
}