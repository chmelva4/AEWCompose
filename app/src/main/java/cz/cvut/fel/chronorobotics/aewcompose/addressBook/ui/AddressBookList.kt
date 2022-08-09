package cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.fel.chronorobotics.aewcompose.R
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.DBAddressBookItem
import cz.cvut.fel.chronorobotics.aewcompose.ui.theme.AEWComposeTheme
import cz.cvut.fel.chronorobotics.aewcompose.util.toHslColor
import kotlin.random.Random

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun TextualAvatar(
    letter: String
) {
    val r = rememberSaveable {
        Random.nextInt(100, 256)
    }
    val g = rememberSaveable {
        Random.nextInt(100, 256)
    }
    val b = rememberSaveable {
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
    name: String,
    addr: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .height(72.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
           TextualAvatar(name.substring(0, 1))
           Spacer(modifier = Modifier.width(16.dp))
           Column(
               modifier = Modifier.fillMaxWidth(),
               verticalArrangement = Arrangement.SpaceBetween
           ) {
               Text(modifier = Modifier.fillMaxWidth(), text = name, style = MaterialTheme.typography.body1)
               Text(
                   modifier = Modifier.fillMaxWidth(),
                   text = addr,
                   style = MaterialTheme.typography.caption,
                   color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)

               )
           }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressBookItemPreview() {
    AEWComposeTheme {
        AddressBookItem("Jhon Doe", "0x4FED1fC4144c223aE3C1553be203cDFcbD38C581")
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
    onItemLongClick: (item: DBAddressBookItem)-> Unit = {},
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
                name = address.name,
                addr = address.ethereumAddress,
                modifier = Modifier.combinedClickable(
                    onClick = {onItemClick(address)},
                    onLongClick = {onItemLongClick(address)},
                )
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
                DBAddressBookItem("0x1C727a55eA3c11B0ab7D3a361Fe0F3C47cE6de5d", "Jhon Doe1"),
                DBAddressBookItem("0x1C727a55eA3c11B0ab7D3a361Fe0F3C47cE6de5d", "Jhon Doe 2"),
            ),
        )
    }
}