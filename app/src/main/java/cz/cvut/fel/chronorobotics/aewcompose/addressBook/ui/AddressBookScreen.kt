package cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.DBAddressBookItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun AddressBookScreen() {

    val vm = getViewModel<AddressBookViewModel>()

    val items by vm.getAddressBookItems().collectAsState(initial = listOf())
    val scope = rememberCoroutineScope()

    var selectedItem: DBAddressBookItem? by remember {
        mutableStateOf(null)
    }

    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var showDropDown by rememberSaveable {
        mutableStateOf(false)
    }

    if (showDialog) {
        AddressBookDialog(
            item = selectedItem,
            onCancel = { showDialog = false },
            onDismiss = { showDialog = false },
            onOk = {
                showDialog = false
                scope.launch {
                    try {
                        if (it.id == 0L) vm.insert(it) else vm.edit(it)
                    } catch (ex: SQLiteConstraintException) {
                        Log.e("AddressBook", ex.message, ex)
                    }

                }
            }
        )
    }

    if (showDropDown) {
        DropdownMenu(expanded = showDropDown, onDismissRequest = { showDropDown = false }) {
            DropdownMenuItem(onClick = { /*TODO*/ }) {
                Text("Delete")
            }
        }
    }

    AddressBookItemList(
        addresses = items,
        onHeaderClick = {
            selectedItem = null
            showDialog = true
        },
        onItemClick = {
            selectedItem = it
            showDialog = true
        },
        onItemLongClick = {
            showDropDown = true
        }
    )

}