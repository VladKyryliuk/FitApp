package app.kyr.fitapp.data

import app.kyr.fitapp.R


sealed class Screens(
    var screen: Int,
    var icon: Int,
    var descriptionIcon: Int
    ) {

    companion object {
        fun getAllScreens(): List<Screens> {
            return listOf(Training, MyTraining, Notification, Profile)
        }
    }

    data object Notification:
        Screens(
            R.string.item_bar_notification,
            R.drawable.notification,
            R.string.description_item_notification
        )
    data object MyTraining:
        Screens(
            R.string.item_bar_MyTraining,
            R.drawable.muscle,
            R.string.description_item_MyTraining
        )
    data object Profile:
        Screens(
            R.string.item_bar_profile,
            R.drawable.person,
            R.string.description_item_profile
        )
    data object Training:
        Screens(
            R.string.item_bar_training,
            R.drawable.dumbbell,
            R.string.description_item_training
        )

    data object exercise:
        Screens(
            R.string.nav_description_exercise,
            R.drawable.dumbbell,
            R.string.description_nav_description_exercise
        )
}