package app.kyr.fitapp.data

import app.kyr.fitapp.R
import app.kyr.fitapp.model.Exercise


class AllTraining {
    fun loadFitsInfo(): List<Exercise> {
        return listOf<Exercise>(
            Exercise(1,R.drawable.jumping, R.string.exercise_jump, R.string.level_medium),
            Exercise(2,R.drawable.jumping2, R.string.exercise_jump2, R.string.level_easy),
            Exercise(3,R.drawable.running, R.string.exercise_run, R.string.level_medium),
            Exercise(4,R.drawable.push_up, R.string.exercise_push_up, R.string.level_hard),
            Exercise(5,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
//            Exercise(6,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
//            Exercise(7,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
//            Exercise(8,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
//            Exercise(9,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
//            Exercise(10,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
//            Exercise(11,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
//            Exercise(12,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
//            Exercise(13,R.drawable.squatting, R.string.exercise_squatting, R.string.level_medium),
            Exercise(14,R.drawable.stretching, R.string.exercise_stretching, R.string.level_hard)
        )
    }

}