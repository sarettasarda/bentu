package trekList.network

data class TrekEvent(
    val trekId: Int,
    val title: String,
    val dateStart: String?,   // keep as String for now (parse later if you want)
    val region: String?,
    val imageUrl: String?,
)

fun TrekPointDto.toEventOrNull(): TrekEvent? {
    val id = trekId ?: return null
    val t = title ?: return null

    val lat = latitudeEnd?.toDoubleOrNull()
    val lon = longitudeEnd?.toDoubleOrNull()

    return TrekEvent(
        trekId = id,
        title = t,
        dateStart = dateStartSchedulation,
        region = regions,
        imageUrl = imagePath,
    )
}
