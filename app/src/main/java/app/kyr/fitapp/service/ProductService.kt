package app.kyr.fitapp.service

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import app.kyr.fitapp.R
import app.kyr.fitapp.data.Screens

import app.kyr.fitapp.model.Exercise
import app.kyr.fitapp.screens.Training

class ProductService {
    companion object{
        var listExercise = mutableListOf<Exercise>()

        fun addExercises(exercises: Exercise) {
            listExercise.add(exercises)
        }

        fun  getListExercises():List<Exercise> {
            return listExercise
        }

//        fun filterTab(currentTab:Screens, filter1:String, filter2: String, ex1:String, ex2:String) {
//            if (currentTab == Screens.Training) {
//                filter1 = ex1
//            }
//            if (currentTab == Screens.MyTraining) {
//                filter2 = ex2
//            }
//        }


        fun openDescription(currentTab: MutableState<Screens>, buttonBack: MutableState<Boolean>,
                            blockNavForDescription:MutableState<Boolean>,
                            selectedExercise:MutableState<Exercise?>,
                            selectedExercise2:MutableState<Exercise?>,
                            navController: NavController,
                            location:String,
                            exercises: Exercise
        ) {
               if (blockNavForDescription.value == true) {
                   buttonBack.value = true
                   navController.navigate(location)
                   selectedExercise.value = exercises
                   selectedExercise2.value = exercises
               }
               else {
                   if (currentTab.value == Screens.Training) {
                       selectedExercise.value = exercises
                   }
                   if (currentTab.value == Screens.MyTraining) {
                       selectedExercise2.value = exercises
                   }
               }
        }

        fun levelColor(exercises: Exercise): Color {
            return when (exercises.complexityId) {
                R.string.level_easy -> Color(0,125,0)
                R.string.level_medium -> Color(232,125,0)
                R.string.level_hard -> Color.Red
                else -> Color.LightGray
            }
        }
    }
}