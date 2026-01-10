package network

import io.ktor.client.HttpClient

class TrekkingItaliaApi(
    private val client: HttpClient = AppHttp.client
) {

}