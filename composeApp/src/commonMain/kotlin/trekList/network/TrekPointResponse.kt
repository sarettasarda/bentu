package trekList.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TreksPointResponse(
    val success: Int? = 0,
    val message: String? = null,
    val data: List<TrekPointDto> = emptyList(),
)

@Serializable
data class TrekPointDto(
    val id: Int? = null,
    @SerialName("trek_id") val trekId: Int? = null,

    val title: String? = null,
    val subtitle: String? = null,

    // Example: "2026-01-10 00:00:00"
    @SerialName("date_start_schedulation")
    val dateStartSchedulation: String? = null,

    val countries: String? = null,
    val regions: String? = null,

    @SerialName("name_start") val nameStart: String? = null,
    @SerialName("name_end") val nameEnd: String? = null,
    val locality: String? = null,

    // Often strings or null in the API response
    @SerialName("latitude_end") val latitudeEnd: String? = null,
    @SerialName("longitude_end") val longitudeEnd: String? = null,

    @SerialName("image_path") val imagePath: String? = null,
)
