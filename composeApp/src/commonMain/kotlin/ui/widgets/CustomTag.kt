package ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ui.Colors
import ui.Dimens

@Composable
fun CustomTag(
    text: String,
    color: Color = Colors.RedBrandColor
) {
    Surface(
        color = color, // Red brand color
        modifier = Modifier.padding(Dimens.medium),
        shape = RoundedCornerShape(Dimens.cardCornerSmall)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(Dimens.x_small),
            style = MaterialTheme.typography.labelMedium
        )
    }
}