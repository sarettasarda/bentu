package trekDetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bentu.composeapp.generated.resources.Res
import bentu.composeapp.generated.resources.trek_country
import bentu.composeapp.generated.resources.trek_departure_date
import bentu.composeapp.generated.resources.trek_description
import bentu.composeapp.generated.resources.trek_difficulty
import bentu.composeapp.generated.resources.trek_email
import bentu.composeapp.generated.resources.trek_enroll
import bentu.composeapp.generated.resources.trek_equipment
import bentu.composeapp.generated.resources.trek_guide
import bentu.composeapp.generated.resources.trek_itinerary
import bentu.composeapp.generated.resources.trek_languages
import bentu.composeapp.generated.resources.trek_length
import bentu.composeapp.generated.resources.trek_max_altitude
import bentu.composeapp.generated.resources.trek_meeting_point
import bentu.composeapp.generated.resources.trek_name
import bentu.composeapp.generated.resources.trek_price
import bentu.composeapp.generated.resources.trek_return_date
import bentu.composeapp.generated.resources.trek_section
import bentu.composeapp.generated.resources.trek_state
import bentu.composeapp.generated.resources.trek_time_length
import bentu.composeapp.generated.resources.trek_transportation
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import trekDetails.network.Guide
import trekDetails.network.Location
import trekDetails.network.Trek
import trekDetails.network.TrekDates
import trekDetails.network.sample
import ui.Colors
import ui.Dimens
import ui.asMoney
import ui.formatIsoToItalian
import ui.widgets.CustomButton
import ui.widgets.CustomTag
import ui.widgets.CustomTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrekDetailScreen(
    trek: Trek = sample,
    onEnroll: () -> Unit = {}, // handle registration
    onBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = trek.title,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .background(Color.White)
                .padding(innerPadding)
        ) {
            // Tags
            Tag(trek.tags)

            // Title & Status
            TitleAndStatus(trek)

            DatesSection(trek.dates)

            HorizontalDivider(modifier = Modifier.height(Dimens.medium).padding(Dimens.small))

            // Technical Info Grid
            TechnicalGrid(trek)

            // Description Section
            Description(trek)

            // Equipment Section
            Equipment(trek.equipment)

            // Location Section
            Location(trek.location)

            //Guide Section
            Guide(trek.guide)

            // Booking Button
            val buttonText = stringResource(Res.string.trek_enroll)
            CustomButton(
                text = buttonText,
                onClick = onEnroll,
            )
        }
    }
}

@Composable
private fun Description(trek: Trek) {
    Column(modifier = Modifier.padding(Dimens.medium)) {
        CustomTitle(
            text = stringResource(Res.string.trek_description),
        )
        Spacer(modifier = Modifier.height(Dimens.spacerMedium))
        Text(
            text = trek.description,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 24.sp
        )
    }
}

@Composable
private fun TitleAndStatus(trek: Trek) {
    Column(modifier = Modifier.padding(Dimens.medium)) {
        Text(
            text = trek.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(Dimens.small))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(Res.string.trek_state),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            trek.participation.status?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Colors.GreenForActive,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun Tag(tags: List<String>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        tags.forEach { tag ->
            when (tag) {
                "NEXT G" -> CustomTag(
                    color = Colors.RedBrandColor,
                    text = "NEXT G"
                )

                "Liguria" -> CustomTag(
                    color = Colors.GreenForActive,
                    text = "Liguria"
                )

                else -> CustomTag(text = tag)
            }
        }
    }
}

@Composable
private fun TechnicalGrid(trek: Trek) {
    val details = listOf(
        Res.string.trek_time_length to trek.details.duration,
        Res.string.trek_difficulty to trek.details.difficulty,
        Res.string.trek_length to "${trek.details.lengthKm} km",
        Res.string.trek_max_altitude to "${trek.details.altitudeM} m",
        Res.string.trek_price to "€ ${trek.participation.fee?.asMoney()}"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(190.dp).padding(Dimens.medium),
        horizontalArrangement = Arrangement.spacedBy(Dimens.medium),
        verticalArrangement = Arrangement.spacedBy(Dimens.medium),
        userScrollEnabled = false
    ) {
        items(details) { item ->
            Column {
                Text(
                    text = stringResource(item.first),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
                Text(
                    item.second?.ifEmpty { "-" } ?: "-",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun DatesSection(dates: TrekDates) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(Res.string.trek_departure_date),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
                Text(
                    text = dates.departure?.let { formatIsoToItalian(it) } ?: "-",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(Res.string.trek_return_date),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
                Text(
                    text = dates.arrival?.let { formatIsoToItalian(it) } ?: "-",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun Equipment(equipment: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.medium)
    ) {
        CustomTitle(stringResource(Res.string.trek_equipment))

        Spacer(modifier = Modifier.height(Dimens.small))

        Column {
            equipment.forEach { item ->
                Row(
                    modifier = Modifier.padding(vertical = Dimens.x_small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(end = Dimens.small)
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun Location(location: Location) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.medium)
    ) {
        CustomTitle(stringResource(Res.string.trek_country))

        Spacer(modifier = Modifier.height(Dimens.small))

        Column {
            TrekDetail(stringResource(Res.string.trek_country), location.country)
            TrekDetail(stringResource(Res.string.trek_meeting_point), location.meetingPoint)
            TrekDetail(stringResource(Res.string.trek_itinerary), location.itinerary)
            TrekDetail(stringResource(Res.string.trek_transportation), location.transportation)
        }
    }
}

@Composable
private fun Guide(guide: Guide) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.medium)
    ) {
        CustomTitle(stringResource(Res.string.trek_guide))

        Spacer(modifier = Modifier.height(Dimens.small))
        Column {
            TrekDetail(stringResource(Res.string.trek_name), guide.name)
            TrekDetail(stringResource(Res.string.trek_section), guide.section)
            TrekDetail(
                stringResource(Res.string.trek_languages),
                guide.languages?.takeIf { it.isNotEmpty() }?.joinToString(", ")
            )
            TrekDetail(stringResource(Res.string.trek_email), guide.email)
        }
    }
}

@Preview
@Composable
private fun EventScreenPreview() {
    MaterialTheme {
        TrekDetailScreen(sample)
    }
}
