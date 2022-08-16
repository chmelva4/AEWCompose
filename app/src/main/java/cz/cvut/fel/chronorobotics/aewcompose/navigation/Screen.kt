package cz.cvut.fel.chronorobotics.aewcompose.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.ui.graphics.vector.ImageVector
import cz.cvut.fel.chronorobotics.aewcompose.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    object AddressBook : Screen("addressBook", R.string.routeIdAddressBook, Icons.Default.MenuBook, R.string.title_addressBoook)
    object ExchangeRates : Screen("exchangeRates", R.string.routeIdExchangeRates, Icons.Default.SwapHoriz, R.string.title_axchangeRates)

    companion object {
        fun findScreenByRoute(route: String): Screen {
            return when(route) {
                ExchangeRates.route -> ExchangeRates
                else -> AddressBook
            }
        }

        fun getTopLevelScreens(): List<Screen> = listOf(AddressBook, ExchangeRates)
    }


}
