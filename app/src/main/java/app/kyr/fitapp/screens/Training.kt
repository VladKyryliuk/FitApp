package app.kyr.fitapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kyr.fitapp.app.LoadingIndicator
import app.kyr.fitapp.component.FitVerticalGrid
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Training(
        filter1:String,countExercise:Int,
        currentTab:MutableState<Screens>,
        navController: NavController,
        buttonBack:MutableState<Boolean>,
        blockNavForDescription:MutableState<Boolean>,
        selectedExercise: MutableState<Exercise?>,
        selectedExercise2:MutableState<Exercise?>,
        exercises:List<Exercise>,
        isLoading: MutableState<Boolean>

     ) {

        Box(modifier = Modifier.fillMaxSize().padding(top = 10.dp,start = 10.dp, end = 10.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            {
                if (isLoading.value){
                    LoadingIndicator(isLoading.value)
                }
                else {

                    FitVerticalGrid(
                        countExercise,
                        currentTab,
                        navController,
                        exerciseList = exercises,
                        selectedFilter = filter1,
                        buttonBack = buttonBack,
                        blockNavForDescription,
                        selectedExercise,
                        selectedExercise2
                    )
                }

            }
        }
}