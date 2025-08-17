package com.semin.watlism.domain.model

import com.semin.watlism.domain.value.Url

sealed interface Person {
    val id: Long
    val name: String
    val profileUrl: Url?

    data class Crew(
        override val id: Long,
        override val name: String,
        override val profileUrl: Url?,
        val job: CrewJob
    ): Person

    data class Actor(
        override val id: Long,
        override val name: String,
        override val profileUrl: Url?,
        val characterName: String
    ): Person
}

enum class CrewJob {
    Director
}