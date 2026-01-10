package network

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

expect object HttpClientFactory {
    fun create(): HttpClient
}

/** Shared configuration applied to every platform engine. */
internal fun HttpClient.configureCommon(): HttpClient = apply {
    // (This function is here just for readability in actuals; see below.)
}

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true      // important
    explicitNulls = true       // optional: include null fields if backend expects them
}