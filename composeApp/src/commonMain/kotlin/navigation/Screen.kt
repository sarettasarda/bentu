package navigation

import trekList.network.TrekEvent

sealed interface Screen {
    object HomeScreen : Screen
    object TrekListScreen : Screen
    class TrekDetailScreen(val trek: TrekEvent) : Screen
}