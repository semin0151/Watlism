package com.semin.watlism.domain.model

sealed interface Role

data class Crew(
    val job: CrewJob
): Role

data class Actor(
    val characterName: String
): Role

enum class CrewJob {
    Director
}