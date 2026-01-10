package trekList.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import trekList.network.GetTreksPointRequest
import trekList.network.TreksPointApi

class TrekListViewModel(
    private val api: TreksPointApi
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _state = MutableStateFlow(TrekListUiState(isLoading = true))
    val state: StateFlow<TrekListUiState> = _state.asStateFlow()

    private var loaded = false

    fun loadIfNeeded(dateIso: String) {
        if (loaded) return
        loaded = true
        load(dateIso)
    }

    fun load(dateIso: String) {
        scope.launch {
            _state.value = TrekListUiState(isLoading = true)

            runCatching {
                api.getEvents(
                    GetTreksPointRequest(
                        position = listOf("Italia"),
                        date = dateIso,
                        maps = false,
                        title = "",
                    )
                )
            }.onSuccess { events ->
                _state.value = TrekListUiState(events = events)
            }.onFailure { e ->
                _state.value = TrekListUiState(error = e.message ?: "Unknown error")
            }
        }
    }

    fun clear() {
        scope.cancel()
    }
}
