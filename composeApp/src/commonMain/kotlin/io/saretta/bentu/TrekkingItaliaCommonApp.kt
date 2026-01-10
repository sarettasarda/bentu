package io.saretta.bentu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import home.HomeScreen
import navigation.Navigator
import navigation.Screen
import trekDetails.ui.TrekDetailScreen
import trekList.ui.TrekListScreen

@Composable
fun TrekkingItaliaCommonApp() {
    val navigator = remember { Navigator() }
    val screen by navigator.screen.collectAsState()

    when (screen) {
        Screen.HomeScreen ->
            HomeScreen(
                onTrekList = { navigator.goTo(Screen.TrekListScreen) }
            )

        is Screen.TrekDetailScreen ->
            TrekDetailScreen(
                // trek = (screen as Screen.TrekDetailScreen).trek,
                onBack = { navigator.goTo(Screen.TrekListScreen) }
            )

        Screen.TrekListScreen ->
            TrekListScreen(
                onTrekClick = { navigator.goTo(Screen.TrekDetailScreen(it)) },
                onBack = { navigator.home() }
            )
    }
}