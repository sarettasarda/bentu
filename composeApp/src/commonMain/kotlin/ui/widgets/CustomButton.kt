package ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ui.ButtonColors
import ui.Dimens

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(Dimens.medium),
        colors = ButtonDefaults.buttonColors(containerColor = ButtonColors.Active),
        shape = RoundedCornerShape(Dimens.small)
    ) {
        Text(text = text, color = Color.White)
    }
}