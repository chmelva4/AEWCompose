package cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.DBAddressBookItem
import cz.cvut.fel.chronorobotics.aewcompose.ui.theme.AEWComposeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddressBookDialog(
    item: DBAddressBookItem? = null,
    onDismiss: ()-> Unit = {},
    onOk: (item: DBAddressBookItem)-> Unit = {},
    onCancel: ()-> Unit = {},
) {
    val okText = if (item == null) "Insert" else "Edit"

    val passedItem = item ?: DBAddressBookItem(0, "", "")

    var nameText by rememberSaveable {
        mutableStateOf(item?.name ?: "")
    }

    var addrText: String by rememberSaveable {
        mutableStateOf(item?.ethereumAddress ?: "")
    }


    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onOk(passedItem)
            })
            { Text(text = okText) }
        },
        dismissButton = {
            TextButton(onClick = onCancel)
            { Text(text = "Cancel") }
        },
        text = {
            Column() {
                TextField(
                    value = nameText ,
                    onValueChange = {
                        nameText = it
                        passedItem.name = it
                                    },
                    label = { Text("Name")}
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = addrText ,
                    onValueChange = {
                        addrText = it
                        passedItem.ethereumAddress = it
                                    },
                    label = { Text("Addr")}
                )
            }
        }
    )
}

@Preview
@Composable
fun dialogPreview() {
    AEWComposeTheme {
        AddressBookDialog() {}
    }
}