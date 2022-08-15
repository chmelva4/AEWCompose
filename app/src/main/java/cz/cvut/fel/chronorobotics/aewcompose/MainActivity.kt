package cz.cvut.fel.chronorobotics.aewcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui.AddressBookScreen
import cz.cvut.fel.chronorobotics.aewcompose.navigation.AEWComposeNavHost
import cz.cvut.fel.chronorobotics.aewcompose.navigation.Screen
import cz.cvut.fel.chronorobotics.aewcompose.theme.AEWComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    AEWComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                   TopAppBar(
                       title = {
                           Text(text = stringResource(Screen.findScreenByRoute(navController.currentDestination?.route?: "").title))
                       },
                       navigationIcon = {
                           IconButton(onClick = {
                               scope.launch { scaffoldState.drawerState.open() }
                           }) {
                               Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                           }
                       }
                   )
                },
                drawerContent = {},
                content = { innerPadding ->
                    AEWComposeNavHost(
                        navController = navController,
                        Modifier.padding(innerPadding)
                    )
                }
            )
        }

    }
}