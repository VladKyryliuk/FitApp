package app.kyr.fitapp.service

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import app.kyr.fitapp.R
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise

class ProductService {
    companion object{
        var listExercise = mutableListOf<Exercise>()

        fun addExercises(exercises: Exercise) {
            listExercise.add(exercises)
        }

        fun  getListExercises():List<Exercise> {
            return listExercise
        }


        fun sortedExercise(type:String,filter1:MutableState<String>, filter2: MutableState<String>, currentTab:MutableState<Screens>){
            if (currentTab.value == Screens.Training) {
                filter1.value = type
            }
            if (currentTab.value == Screens.MyTraining){
                filter2.value = type
            }
        }

        fun exerciseLevel(selectedFilter:String, exerciseList:List<Exercise>):List<Exercise> {
         return when (selectedFilter) {
                "All" -> exerciseList
                "Hard" -> exerciseList.filter { it.complexityId == R.string.level_hard }
                "Middle" -> exerciseList.filter { it.complexityId == R.string.level_medium }
                "Easy" -> exerciseList.filter { it.complexityId == R.string.level_easy }
                else -> exerciseList
            }
        }


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
                   navController.navigate(route = location)
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