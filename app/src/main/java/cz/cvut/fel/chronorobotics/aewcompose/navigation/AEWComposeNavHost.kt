package cz.cvut.fel.chronorobotics.aewcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui.AddressBookScreen
import cz.cvut.fel.chronorobotics.aewcompose.exchangeRates.ui.ExchangeRatesScreen

@Composable
fun AEWComposeNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.AddressBook.route, modifier = modifier) {
        composable(Screen.AddressBook.route) { AddressBookScreen(navController as NavController)}
        composable(Screen.ExchangeRates.route) { ExchangeRatesScreen(navController as NavController) }
    }
}