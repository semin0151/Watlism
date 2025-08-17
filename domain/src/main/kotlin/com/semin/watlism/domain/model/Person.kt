package com.semin.watlism.domain.model

sealed interface Person {
    val id: Long
    val name: String
    val profileUrl: String?

    data class Crew(
        override val id: Long,
        override val name: String,
        override val profileUrl: String?,
        val job: CrewJob
    ): Person

    data class Actor(
        override val id: Long,
        override val name: String,
        override val profileUrl: String?,
        val characterName: String
    ): Person
}

enum class CrewJob {
    Director
}