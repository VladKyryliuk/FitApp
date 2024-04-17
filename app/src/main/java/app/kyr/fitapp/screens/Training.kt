package app.kyr.fitapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.kyr.fitapp.component.FitVerticalGrid
import app.kyr.fitapp.data.AllTraining
import app.kyr.fitapp.data.Screens
import app.kyr.fitapp.model.Exercise


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Training(
        filter1:String,countExercise:Int,
        currentTab:MutableState<Screens>,
        navController: NavController,
        buttonBack:MutableState<Boolean>,
        blockNavForDescription:MutableState<Boolean>,
        selectedExercise: MutableState<Exercise?>,
        selectedExercise2:MutableState<Exercise?>

     ) {

        Box(modifier = Modifier.fillMaxSize().padding(top = 10.dp,start = 10.dp, end = 10.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            {

                FitVerticalGrid(
                    countExercise,
                    currentTab,
                    navController,
                    exerciseList = AllTraining().loadFitsInfo(),
                    selectedFilter = filter1,
                    buttonBack = buttonBack,
                    blockNavForDescription,
                    selectedExercise,
                    selectedExercise2
                )

            }
        }
//    }
}