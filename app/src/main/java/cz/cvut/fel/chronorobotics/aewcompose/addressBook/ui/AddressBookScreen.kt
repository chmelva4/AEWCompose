package cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.DBAddressBookItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun AddressBookScreen() {

    val vm = getViewModel<AddressBookViewModel>()

    val items by vm.getAddressBookItems().collectAsState(initial = listOf())
    val scope = rememberCoroutineScope()

    var selectedItem: DBAddressBookItem? by rememberSaveable {
        mutableStateOf(null)
    }

    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (showDialog) {
        AddressBookDialog(
            item = selectedItem ?: DBAddressBookItem(0L, "", ""),
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

    AddressBookItemList(
        addresses = items,
        onHeaderClick = {
            selectedItem = null
            showDialog = true
        },
        onItemClick = {

        },
        onEdit = {
            selectedItem = it
            showDialog = true
        },
        onDelete = {
            scope.launch {
                vm.delete(it)
            }
        }
    )

}