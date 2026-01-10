package ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomTitleL(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray,
        modifier = modifier
    )
}

@Composable
fun CustomTitleM(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray,
        modifier = modifier
    )
}

@Composable
fun CustomHeadlineM(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun CustomBodyL(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
fun CustomNavigationText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        maxLines = 1,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
@Preview
private fun CustomTitlePreview() {
    Column {
        CustomHeadlineM(text = "Custom Headline M")
        CustomTitleL(text = "Custom Title L")
        CustomTitleM(text = "Custom Title M")
        CustomBodyL(text = "Custom Body L")
        CustomNavigationText(text = "Custom Navigation Text")
    }
}