package network

import io.ktor.client.HttpClient

object AppHttp {
    val client: HttpClient by lazy { HttpClientFactory.create() }
}
