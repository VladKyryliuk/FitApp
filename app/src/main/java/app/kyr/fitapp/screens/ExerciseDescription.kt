package app.kyr.fitapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.kyr.fitapp.component.DescriptionExercise
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise

@Composable
fun ExerciseDescription(selectedExercise:Exercise?) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        {
            DescriptionExercise(exercises = selectedExercise)
        }
    }
}