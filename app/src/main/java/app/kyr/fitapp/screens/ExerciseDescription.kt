package app.kyr.fitapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.kyr.fitapp.component.DescriptionExercise
import app.kyr.fitapp.component.FitVerticalGrid
import app.kyr.fitapp.data.AllTraining
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDescription(currentTab:MutableState<Screens>,navController: NavController) {
    Column (modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = Screens.exercise.screen),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    if (currentTab.value == Screens.Training) {
                        navController.navigate("Training")
                    }
                    if (currentTab.value == Screens.MyTraining) {
                        navController.navigate("My Training")
                    }
                }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
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
            )
            {
                DescriptionExercise(exercises = Exercise.returnExercise())

            }
        }
    }
}