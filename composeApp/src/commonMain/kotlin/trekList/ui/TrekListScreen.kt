package trekList.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import bentu.composeapp.generated.resources.Res
import bentu.composeapp.generated.resources.home_page_trek_list_button
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import trekList.network.TrekEvent
import trekList.presenter.TrekListViewModel
import ui.BackgroundColors
import ui.Dimens
import ui.formatIsoToItalian
import ui.widgets.CustomTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrekListScreen(
    onTrekClick: (TrekEvent) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val trekListViewModel: TrekListViewModel = koinInject()

    val state by trekListViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        trekListViewModel.loadIfNeeded("2026-01-10T00:00:00.000Z")
    }

    DisposableEffect(Unit) {
        onDispose { trekListViewModel.clear() }
    }

    when {
        state.isLoading -> {} //LoadingUi()
        state.error != null -> {}// ErrorUi(state.error)
        else -> TrekListScreenStateless(state.events, onBack, onTrekClick)
    }
}

@Composable
private fun TrekListScreenStateless(
    trekList: List<TrekEvent> = emptyList(),
    onBack: () -> Unit = {},
    onTrekClick: (TrekEvent) -> Unit = {}
) {
    Scaffold(
        containerColor = BackgroundColors.Light,
        topBar = {
            CustomTopBar(
                title = stringResource(Res.string.home_page_trek_list_button),
                onBack = onBack
            )
        }) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimens.none),
            modifier = Modifier.fillMaxWidth()
                .padding(innerPadding)
        ) {
            items(trekList, key = { it.trekId }) { trek ->
                TrekCard(trek = trek, onClick = { onTrekClick(trek) })
            }
        }
    }
}

@Composable
private fun TrekCard(trek: TrekEvent, onClick: (() -> Unit)? = null) {
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
                text = trek.dateStart?.let { formatIsoToItalian(it) } ?: "-",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = Dimens.x_small, bottom = Dimens.small)
            )
        }
    }
}

@Preview
@Composable
fun TrekListScreenPreview() {
    MaterialTheme {
        TrekListScreenStateless(trekList = sampleEvents)
    }
}

private val sampleEvents = listOf(
    TrekEvent(
        trekId = 1,
        title = "NEXT G: Tra mirto e mare a Varazze (SV)",
        dateStart = "2026-01-17 00:00:00",
        region = "Ligura",
        imageUrl = "https://bfb.trekkingitalia.org/storage/media/images/original/804921a08ea369d05ef0b3d8af11111f.webp"
    ),
    TrekEvent(
        trekId = 2,
        title = "Valmadrera: poggi e scorci",
        dateStart = "2026-01-23 00:00:00",
        region = "Emilia Romagna",
        imageUrl = "https://bfb.trekkingitalia.org/storage/media/images/original/1093d991632891bc3d6f016a6f9f9ff1.webp"
    )
)