package network

import io.ktor.client.HttpClient

expect object HttpClientFactory {
    fun create(): HttpClient
}

/** Shared configuration applied to every platform engine. */
internal fun HttpClient.configureCommon(): HttpClient = apply {
    // (This function is here just for readability in actuals; see below.)
}
