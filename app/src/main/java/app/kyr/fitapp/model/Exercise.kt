package app.kyr.fitapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Exercise (
    var id:Int,
    @DrawableRes var imageId: Int,
    @StringRes var descriptionId: Int,
    @StringRes var complexityId: Int
)
{
//    companion object {
//
//       var exercise: Exercise? = null
//
//        fun saveExercise(id: Int, imageId: Int, descriptionId: Int, complexityId: Int) {
//            exercise = Exercise(id,imageId,descriptionId,complexityId)
//        }
//
//        fun returnExercise(): Exercise? {
//            return exercise
//        }
//    }
}