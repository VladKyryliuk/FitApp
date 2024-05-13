package app.kyr.fitapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.kyr.fitapp.component.DescriptionExercise
import app.kyr.fitapp.model.Exercise

@Composable
fun ExerciseDescriptionForMyTraining(selectedExercise2: Exercise?) {
    Box(modifier = Modifier.fillMaxSize().padding(start = 5.dp, end = 5.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        {
            DescriptionExercise(exercises = selectedExercise2)

        }
    }
}