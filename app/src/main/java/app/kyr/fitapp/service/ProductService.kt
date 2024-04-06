package app.kyr.fitapp.service

import androidx.compose.ui.graphics.Color
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