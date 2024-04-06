package app.kyr.fitapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.kyr.fitapp.R
import app.kyr.fitapp.data.AllTraining
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise
import app.kyr.fitapp.screens.ExerciseDescription
import app.kyr.fitapp.service.ProductService
import app.kyr.fitapp.service.ProductService.Companion.levelColor

@Composable
fun FitVerticalGrid(countExercise:Int,currentTab: MutableState<Screens>, navController: NavController, exerciseList: List<Exercise>,
                    selectedFilter:String, modifier: Modifier = Modifier) {

    val filteredList = when (selectedFilter) {
        "All" -> exerciseList
        "Hard" -> exerciseList.filter { it.complexityId == R.string.level_hard }
        "Middle" -> exerciseList.filter { it.complexityId == R.string.level_medium }
        "Easy" -> exerciseList.filter { it.complexityId == R.string.level_easy }
        else -> exerciseList
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(countExercise),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(filteredList) { exercise ->
            CardItem(
                exercises = exercise,
                navController = navController,
                currentTab
            )
        }
    }
}


@Composable
fun DescriptionExercise(exercises: Exercise) {
    Column {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = exercises.imageId),
                contentDescription = stringResource(id = exercises.descriptionId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = exercises.descriptionId),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = stringResource(id = exercises.complexityId),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp, bottom = 2.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W500,
                    color = levelColor(exercises)

                )
            )
        }
    }
}


@Composable
fun CardItem(
    exercises:Exercise,
    navController: NavController,
    currentTab: MutableState<Screens>
    ){
    var description: String = stringResource(id = Screens.exercise.screen)
    Card ( modifier = Modifier
        .height(160.dp)
        .width(150.dp)
        .clickable {
            currentTab.value = Screens.exercise

            navController.navigate(description)

            Exercise.saveExercise(exercises.id, exercises.imageId,exercises.descriptionId,exercises.complexityId)

        }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray) ,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image (
                painter = painterResource(id = exercises.imageId ),
                contentDescription = stringResource(id = exercises.descriptionId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = exercises.descriptionId),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = stringResource(id = exercises.complexityId),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp, bottom = 2.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W500,
                    color = levelColor(exercises)

                )
            )
            Button(
                onClick = { ProductService.addExercises(exercises)},
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .padding(top = 3.dp, bottom = 5.dp)
            ) {
                Text(
                    text = "Додати",
                    fontSize = 12.sp
                    )
            }
        }
    }
}


//@Composable
//fun MyDropDownMenu(showMenu: Boolean, filter1: String, filter2: String, currentTab: Screens) {
//    DropdownMenu(
//        expanded = showMenu,
//        onDismissRequest = { showMenu = false },
//        modifier = Modifier.width(70.dp)
//    ) {
//        DropdownMenuItem(
//            text = { Text(text = "All") },
//            onClick = {
//                if (currentTab == Screens.Training) {
//                    filter1 = "All"
//                }
//                if(currentTab == Screens.MyTraining) {
//                    filter2 = "All"
//                }
//
//            }
//        )
//        DropdownMenuItem(
//            text = { Text(text = "Hard") },
//            onClick = {
//                if (currentTab == Screens.Training) {
//                    filter1 = "Hard"
//                }
//                if(currentTab == Screens.MyTraining) {
//                    filter2 = "Hard"
//                }
//            }
//        )
//        DropdownMenuItem(
//            text = { Text(text = "Middle") },
//            onClick = {
//                if (currentTab == Screens.Training) {
//                    filter1 = "Middle"
//                }
//                if(currentTab == Screens.MyTraining) {
//                    filter2 = "Middle"
//                }
//            }
//        )
//        DropdownMenuItem(
//            text = { Text(text = "Easy") },
//            onClick = {
//                if (currentTab == Screens.Training) {
//                    filter1 = "Easy"
//                }
//                if(currentTab == Screens.MyTraining) {
//                    filter2 = "Easy"
//                }
//            }
//        )
//    }
//}


@Composable
@Preview(showSystemUi = true)
fun PreviewDescriptionExercise() {
    DescriptionExercise(Exercise (
        1,
        R.drawable.jumping,
        R.string.exercise_jump,
        R.string.level_medium
    ))
}

//@Composable
//@Preview(showSystemUi = true)
//fun PreviewCardItem() {
//    CardItem(
//        Exercise (
//            R.drawable.jumping,
//            R.string.exercise_jump,
//            R.string.level_medium
//        )
//        ,
//        onItemClick =
//    )
//}

//@Composable
//@Preview(showSystemUi = true)
//fun PreviewItemList() {
//   FitVerticalGrid(exerciseList = AllTraining().loadFitsInfo(), selectedFilter = "Hard")
//}