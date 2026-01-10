package trekList.presenter

import trekList.network.TrekEvent

data class TrekListUiState(
    val isLoading: Boolean = false,
    val events: List<TrekEvent> = emptyList(),
    val error: String? = null
)