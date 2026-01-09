package trekDetails.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trek(
    val id: String,
    val title: String,
    val url: String,
    val dates: TrekDates,
    @SerialName("technical_details")
    val details: TechnicalDetails,
    val participation: Participation,
    val description: String,
    @SerialName("equipment_recommended")
    val equipment: List<String>,
    val tags: List<String>,
    val guide: Guide,
    val location: Location,
)

@Serializable
data class TrekDates(
    val departure: String? = null,
    val arrival: String? = null,
    @SerialName("registration_deadline")
    val registrationDeadline: String? = null
)

@Serializable
data class TechnicalDetails(
    val difficulty: String? = null,
    val duration: String? = null,
    @SerialName("total_length_km")
    val lengthKm: Int?= null,
    @SerialName("max_altitude_m")
    val altitudeM: Int?= null,
)

@Serializable
data class Participation(
    val fee: Double? = null,
    val status: String? = null,
    @SerialName("min_participants")
    val minParticipants: Int? = null,
    @SerialName("max_participants")
    val maxParticipants: Int? = null,
)

@Serializable
data class Location(
    val country: String? = null,
    @SerialName("meeting_point")
    val meetingPoint: String? = null,
    val itinerary: String? = null,
    val transportation: String? = null,
)

@Serializable
data class Guide(
    val name: String? = null,
    val section: String? = null,
    val languages: List<String>? = emptyList(),
    val email: String? = null,
)

//TODO: remove this sample and use real data from API
val sample = Trek(
    id = "235312",
    url = "https://trekkingitalia.org/trek/235312",
    title = "NEXT G: Tra mirto e mare a Varazze (SV)",
    dates = TrekDates(
        departure = "2026-01-10",
        arrival = "2026-01-10",
        registrationDeadline = "2026-01-05"
    ),
    details = TechnicalDetails(
        difficulty = "Facile / medio",
        duration = "5h",
        lengthKm = 10,
        altitudeM = 400
    ),
    participation = Participation(
        fee = 6.0,
        status = "Attivo",
        minParticipants = 5,
        maxParticipants = 20
    ),
    description = "NEXT G identifica i trek dedicati ai soci giovani (Next Generation). Un trekking ad anello semplice e panoramico che, partendo da Varazze, risale le pendici del Monte Grosso fino al Santuario della Madonna della Guardia. Il sentiero si sviluppa lungo antiche mulattiere immerse nella macchia mediterranea tra pini e mirto.",
    equipment = listOf(
        "Borraccia da 1 litro",
        "Coprizaino",
        "Giacca impermeabile traspirante",
        "Lampada frontale + pile ricarica",
        "Guanti e cappello in lana o pile",
        "Scarponi da trekking",
        "Thermos",
        "Zaino giornaliero 20-25 litri"
    ),
    location = Location(
        country = "Italia",
        meetingPoint = "Varazze, Piazza San Domenico",
        itinerary = "Varazze - Monte Grosso - Santuario della Madonna della Guardia - Varazze",
        transportation = "Treno fino a Varazze, poi a piedi"
    ),
    guide = Guide(
        name = "Nonna Lella",
        section = "Liguria",
        languages = listOf("Italiano", "Inglese"),
        email = "genova@trekkingitalia.org"
    ),
    tags = listOf(
        "NEXT G",
        "Liguria"
    )
)