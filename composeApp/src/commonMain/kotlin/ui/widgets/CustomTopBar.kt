package ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(title: String, onBack: () -> Unit = {}) {
    Column {
        TopAppBar(
            title =
                { CustomNavigationText(title) },
            navigationIcon =
                {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = Color.White,
                titleContentColor = Color.DarkGray
            )
        )
        HorizontalDivider(thickness = 0.5.dp)
    }
}

@Composable
@Preview
fun CustomTopBarPreview() {
    CustomTopBar("Title text") {}
}