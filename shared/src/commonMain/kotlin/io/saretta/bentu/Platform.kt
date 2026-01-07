package io.saretta.bentu

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform