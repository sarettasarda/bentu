package navigation

import trekDetails.network.Trek

sealed interface Screen {
    object HomeScreen : Screen
    object TrekListScreen : Screen
    class TrekDetailScreen(val trek: Trek) : Screen
}