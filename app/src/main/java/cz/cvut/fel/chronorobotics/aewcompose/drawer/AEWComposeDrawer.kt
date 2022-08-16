package cz.cvut.fel.chronorobotics.aewcompose.drawer

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import cz.cvut.fel.chronorobotics.aewcompose.R
import cz.cvut.fel.chronorobotics.aewcompose.theme.AEWComposeTheme

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 150.dp)
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary,
            fontWeight= FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}

@Composable
@Preview(heightDp = 300, widthDp = 500)
fun drawerHeaderPreview(){
    AEWComposeTheme {
        DrawerHeader()
    }
}

@Composable
fun DrawerItem(
    item: NavDrawerItem,
    onItemClicked: (NavDrawerItem) -> Unit
) {
    val mod = Modifier
        .height(56.dp)
        .padding(vertical = 8.dp, horizontal = 8.dp)
        .clip(RoundedCornerShape(4.dp))
        .clickable { onItemClicked(item) }
        .fillMaxWidth()

    var rowMod = Modifier.padding()
    var tint = MaterialTheme.colors.onSurface
    if (item.isSelected) {
        rowMod = rowMod.background(color = MaterialTheme.colors.primary.copy(alpha = 0.15f))
        tint = MaterialTheme.colors.primary
    }


    Surface(modifier = mod) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowMod
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(imageVector = item.icon, contentDescription = null, tint = tint)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = item.title),
                style = MaterialTheme.typography.subtitle1,
                color = tint
            )
        }

    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun selectedItemPreviewNight() {
    AEWComposeTheme {
        DrawerItem(
            item = NavDrawerItem("", R.string.title_addressBoook, Icons.Default.MenuBook, true),
            onItemClicked = {}
        )
    }
}

@Composable
@Preview()
fun selectedItemPreview() {
    AEWComposeTheme {
        DrawerItem(
            item = NavDrawerItem("", R.string.title_addressBoook, Icons.Default.MenuBook, true),
            onItemClicked = {}
        )
    }
}

@Composable
fun AEWComposeDrawer(
    drawerItems: List<NavDrawerItem>,
    onItemClicked: (NavDrawerItem)->Unit
) {
    Column {
        DrawerHeader()
        LazyColumn() {
            items(drawerItems) { item ->
                DrawerItem(item = item, onItemClicked = onItemClicked)
            }
        }
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES, widthDp = 250, heightDp = 500, showBackground = true)
fun drawerPreview() {
    AEWComposeTheme {
        AEWComposeDrawer(drawerItems = listOf(
            NavDrawerItem("", R.string.title_addressBoook, Icons.Default.MenuBook, true),
            NavDrawerItem("", R.string.title_addressBoook, Icons.Default.MenuBook, false)
        ), onItemClicked = {})
    }
}

