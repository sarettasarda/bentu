package navigation

import kotlinx.coroutines.flow.*

class Navigator {
    private val _screen = MutableStateFlow<Screen>(Screen.HomeScreen)
    val screen: StateFlow<Screen> = _screen.asStateFlow()

    fun goTo(screen: Screen) {
        _screen.value = screen
    }

    fun back() {
        _screen.value = Screen.HomeScreen
    }
}