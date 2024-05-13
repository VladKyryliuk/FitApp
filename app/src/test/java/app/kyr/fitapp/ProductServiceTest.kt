package app.kyr.fitapp
import androidx.compose.ui.graphics.Color
import app.kyr.fitapp.data.AllTraining
import app.kyr.fitapp.model.Exercise
import app.kyr.fitapp.model.ProfileViewModel
import app.kyr.fitapp.service.ProductService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Assert
import org.junit.Test


class ProductServiceTest {
    @Test
    fun testExerciseLevelFilter() {
        val exerciseList = listOf(
            Exercise(1, 1, R.string.exercise_jump, R.string.level_easy),
            Exercise(2, 2, R.string.exercise_run, R.string.level_medium),
            Exercise(3, 3, R.string.exercise_push_up, R.string.level_hard)
        )

        val filteredExercises = ProductService.exerciseLevel("Easy", exerciseList)
        assertEquals(1, filteredExercises.size)
        assertEquals(R.string.level_easy, filteredExercises[0].complexityId)
    }

    @Test
    fun testLevelColor() {
        val easyExercise = Exercise(1, 1, R.string.exercise_jump, R.string.level_easy)
        val mediumExercise = Exercise(2, 2, R.string.exercise_run, R.string.level_medium)
        val hardExercise = Exercise(3, 3, R.string.exercise_push_up, R.string.level_hard)

        val easyColor = ProductService.levelColor(easyExercise)
        val mediumColor = ProductService.levelColor(mediumExercise)
        val hardColor = ProductService.levelColor(hardExercise)

        assertEquals(Color(0, 125, 0), easyColor)
        assertEquals(Color(232, 125, 0), mediumColor)
        assertEquals(Color.Red, hardColor)
    }

    @Test
    fun testSortedExercise() {
        val exerciseList = listOf(
            Exercise(1, 1, R.string.exercise_jump, R.string.level_easy),
            Exercise(2, 2, R.string.exercise_run, R.string.level_medium),
            Exercise(3, 3, R.string.exercise_push_up, R.string.level_hard)
        )

        val filteredExercises = ProductService.exerciseLevel("Easy", exerciseList)
        assertEquals(1, filteredExercises.size)
        assertEquals(R.string.level_easy, filteredExercises[0].complexityId)
    }

    @Test
    fun testProfileSave() {
        val profileViewModel = ProfileViewModel()

        profileViewModel.saveProfileData("John", "30", "Bio")

        assertEquals("John", profileViewModel.name)
        assertEquals("30", profileViewModel.age)
        assertEquals("Bio", profileViewModel.bio)
    }

    @Test
    fun testGetListExercisesForAnotherScreen(){
        val listExercise = mutableListOf<Exercise>()

        val exercise = Exercise(1,R.drawable.jumping,
            R.string.exercise_jump,R.string.description_nav_description_exercise);

        listExercise.add(exercise);
        listExercise.add(exercise);

        ProductService.addExercises(listExercise.get(0));
        ProductService.addExercises(listExercise.get(1));

        assertNotNull(ProductService.getListExercises());
        assertEquals(listExercise,ProductService.getListExercises())
    }

    @Test
    fun testLoadFitsInfo() {
        val allTraining = AllTraining()
        val exerciseList = allTraining.loadFitsInfo()

        Assert.assertEquals(6, exerciseList.size)
    }
}