package app.kyr.fitapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import app.kyr.fitapp.app.FitApp
import app.kyr.fitapp.screens.MyTraining
import app.kyr.fitapp.screens.Notification
import app.kyr.fitapp.screens.Profile
import app.kyr.fitapp.screens.Training
import app.kyr.fitapp.ui.theme.FitAppTheme
import app.kyr.fitapp.ui.theme.LightBlue
import app.kyr.fitapp.ui.theme.LightGrayPlus


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            val displayMetrics = resources.displayMetrics
            FitAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FitApp(windowSize)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Tag: ","start")

    }
    override fun onResume() {
        super.onResume()
        Log.d("Tag: ","resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Tag: ","pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Tag: ","stop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Tag: ","restart")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Tag: ","destroy")
    }
}

