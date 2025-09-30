package com.semin.watlism.domain.model

sealed interface Role

data class Director(
    val name: String = "Director"
): Role

data class Actor(
    val characterName: String
): Role

enum class CrewJob {
    Directing, Acting
}