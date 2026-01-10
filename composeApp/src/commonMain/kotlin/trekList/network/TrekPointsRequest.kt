package trekList.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTreksPointRequest(
    val position: List<String> = listOf("Italia"),
    val regions: String? = null,
    val destination: String? = null,
    val typology: List<String> = emptyList(),
    val date: String,                 // ISO string e.g. 2026-01-10T19:41:45.311Z
    val dateEnd: String? = null,
    val label: Int? = null,
    val departments: List<String> = emptyList(),
    val difficulty: List<String> = emptyList(),
    val maps: Boolean = false,
    val title: String = "",
    @SerialName("type_trek")
    val typeTrek: String? = null
)
