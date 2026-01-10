package navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Navigator {
    private val _screen = MutableStateFlow<Screen>(Screen.HomeScreen)
    val screen: StateFlow<Screen> = _screen.asStateFlow()

    fun goTo(screen: Screen) {
        _screen.value = screen
    }

    fun home() {
        _screen.value = Screen.HomeScreen
    }
}