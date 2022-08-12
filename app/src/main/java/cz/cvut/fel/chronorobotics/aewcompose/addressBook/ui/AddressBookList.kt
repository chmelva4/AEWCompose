@file:OptIn(ExperimentalFoundationApi::class)

package cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.fel.chronorobotics.aewcompose.R
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.DBAddressBookItem
import cz.cvut.fel.chronorobotics.aewcompose.ui.theme.AEWComposeTheme
import kotlin.random.Random
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun TextualAvatar(
    letter: String
) {
    val r = rememberSaveable(letter) {
        Random.nextInt(100, 256)
    }
    val g = rememberSaveable(letter) {
        Random.nextInt(100, 256)
    }
    val b = rememberSaveable(letter) {
        Random.nextInt(100, 256)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .background(color = Color(r, g, b), CircleShape)
    ) {
        Text(
            text = letter,
            modifier = Modifier,
            style = MaterialTheme.typography.subtitle1,
            color = Color.White,
            textAlign = TextAlign.Center

        )
    }

}

@Preview(showBackground = true)
@Composable
fun TextualAvatarPreview() {
    AEWComposeTheme {
        TextualAvatar("A")
    }
}

@Composable
fun AddressBookItem(
    item: DBAddressBookItem,
    onItemClick: (item: DBAddressBookItem) -> Unit = {},
    onItemEdit: (item: DBAddressBookItem) -> Unit = {},
    onItemDelete: (item: DBAddressBookItem) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var showDropDown by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier
            .height(72.dp)
            .combinedClickable(onLongClick = { showDropDown = true}) { onItemClick(item) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
           TextualAvatar(item.name.substring(0, 1))
           Spacer(modifier = Modifier.width(16.dp))
           Column(
               modifier = Modifier.fillMaxWidth(),
               verticalArrangement = Arrangement.SpaceBetween
           ) {
               Text(modifier = Modifier.fillMaxWidth(), text = item.name, style = MaterialTheme.typography.body1)
               Text(
                   modifier = Modifier.fillMaxWidth(),
                   text = item.ethereumAddress,
                   style = MaterialTheme.typography.caption,
                   color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)

               )
           }
        }
        if (showDropDown) {
            DropdownMenu(expanded = showDropDown, onDismissRequest = { showDropDown = false }) {
                DropdownMenuItem(onClick = { onItemEdit(item) }) {
                    Text("Edit")
                }
                DropdownMenuItem(onClick = { onItemDelete(item) }) {
                    Text("Delete")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressBookItemPreview() {
    AEWComposeTheme {
        AddressBookItem(DBAddressBookItem(0,"Jhon Doe", "0x4FED1fC4144c223aE3C1553be203cDFcbD38C581"))
    }
}

@Composable
fun AddressBookListHeader(onHeaderClick: () -> Unit) {
    Surface(
        Modifier
            .height(56.dp)
            .fillMaxWidth()
            .clickable { onHeaderClick() }
    ){
        Row(Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Add,
                null,
                tint = MaterialTheme.colors.primaryVariant
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = stringResource(id = R.string.add_address_book_item))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddressBookItemList(
    addresses: List<DBAddressBookItem>,
    onItemClick: (item: DBAddressBookItem)-> Unit = {},
    onEdit: (item: DBAddressBookItem)-> Unit = {},
    onDelete: (item: DBAddressBookItem)-> Unit = {},
    onHeaderClick: ()-> Unit = {},
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            AddressBookListHeader() {
                onHeaderClick()
            }
        }
        items(addresses) { address ->
            AddressBookItem(
                item = address,
                onItemClick = onItemClick,
                onItemEdit = onEdit,
                onItemDelete = onDelete,
            )

        }
    }
}

@Preview
@Composable
fun AddressBookListPreview() {
    AEWComposeTheme {
        AddressBookItemList(
            addresses = listOf(
                DBAddressBookItem(0, "0x1C727a55eA3c11B0ab7D3a361Fe0F3C47cE6de5d", "Jhon Doe1"),
                DBAddressBookItem(0, "0x1C727a55eA3c11B0ab7D3a361Fe0F3C47cE6de5d", "Jhon Doe 2"),
            ),
        )
    }
}