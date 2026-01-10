package trekList.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import network.AppHttp

class TreksPointApi(
    private val client: HttpClient = AppHttp.client
) {
    suspend fun getTreksPoint(request: GetTreksPointRequest): TreksPointResponse {
        return client.post("https://bff.trekkingitalia.org/api/v1/get-treks-point") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun getEvents(request: GetTreksPointRequest): List<TrekEvent> {
        val resp = getTreksPoint(request)
        return resp.data.mapNotNull { it.toEventOrNull() }
    }
}