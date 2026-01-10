package io.saretta.bentu

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    di.initKoin()
    ComposeViewport {
        TrekkingItaliaCommonApp()
    }
}