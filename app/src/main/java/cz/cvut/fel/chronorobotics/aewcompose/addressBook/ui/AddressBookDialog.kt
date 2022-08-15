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
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.DBAddressBookItem
import cz.cvut.fel.chronorobotics.aewcompose.theme.AEWComposeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddressBookDialog(
    item: DBAddressBookItem = DBAddressBookItem(0L, "", ""),
    onDismiss: ()-> Unit = {},
    onOk: (item: DBAddressBookItem)-> Unit = {},
    onCancel: ()-> Unit = {},
) {
    val okText = if (item.id == 0L) "Insert" else "Edit"

    var name by rememberSaveable(item) {
        mutableStateOf(item.name)
    }
    var addr by rememberSaveable(item) {
        mutableStateOf(item.ethereumAddress)
    }


    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                item.name = name
                item.ethereumAddress = addr
                onOk(item)
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
                    value = name ,
                    onValueChange = {
                        name = it
                                    },
                    label = { Text("Name")}
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = addr ,
                    onValueChange = {
                        addr = it
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