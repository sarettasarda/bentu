package treks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.ui.tooling.preview.Preview
import trekDetails.network.Trek
import trekDetails.network.sample
import ui.Dimens
import ui.formatIsoToItalian

@Composable
fun TrekListScreen(
    trekList: List<Trek> = sampleEvents,
    onTrekClick: (Trek) -> Unit = {},
    onBack: () -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = Dimens.medium),
        verticalArrangement = Arrangement.spacedBy(Dimens.none),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(trekList, key = { it.id }) { trek ->
            TrekCard(trek = trek, onClick = { onTrekClick(trek) })
        }
    }
}

@Composable
private fun TrekCard(trek: Trek, onClick: (() -> Unit)? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.medium, vertical = Dimens.small)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(Dimens.cardCornerSmall),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation)
    ) {
        Column(modifier = Modifier.padding(Dimens.medium)) {
            Text(
                text = trek.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = trek.dates.departure?.let { formatIsoToItalian(it) } ?: "-",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = Dimens.x_small, bottom = Dimens.small)
            )
            Text(
                text = trek.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun TrekListScreenPreview() {
    MaterialTheme {
        TrekListScreen()
    }
}

private val sampleEvents = listOf(
    sample, sample.copy(id = "234")
)