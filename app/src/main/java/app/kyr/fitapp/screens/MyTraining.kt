package app.kyr.fitapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.kyr.fitapp.component.FitVerticalGrid
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise
import app.kyr.fitapp.service.ProductService

@Composable
fun MyTraining(
    filter2:String,
    countExercise:Int,
    currentTab:MutableState<Screens>,
    navController: NavController,
    buttonBack:MutableState<Boolean>,
    blockNavForDescription:MutableState<Boolean>,
    selectedExercise:MutableState<Exercise?>,
    selectedExercise2:MutableState<Exercise?>,
    exercises:List<Exercise>
    ) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            if(ProductService.getListExercises().isEmpty()){
                Text(text = "You haven't added any exercise")
            }
            FitVerticalGrid(
                countExercise,
                currentTab,
                navController,
                exerciseList = exercises,
                selectedFilter = filter2,
                buttonBack,
                blockNavForDescription,
                selectedExercise,
                selectedExercise2
            )

        }
    }
}