package com.semin.watlism.data.model

import com.semin.watlism.domain.model.Actor
import com.semin.watlism.domain.model.Credit
import com.semin.watlism.domain.model.Credits
import com.semin.watlism.domain.model.CrewJob
import com.semin.watlism.domain.model.Director
import com.semin.watlism.domain.model.Person
import com.semin.watlism.domain.value.PersonId
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.Url

data class MovieCreditsData(
    val cast: List<CreditsData>,
    val crew: List<CreditsData>
) {
    fun toCredits(titleId: TitleId) = Credits(
        cast = cast.map { it.toCredit(titleId) },
        crew = crew.map { it.toCredit(titleId) }
    )
}

data class SeriesCreditsData(
    val cast: List<CreditsData>,
    val crew: List<CreditsData>
) {
    fun toCredits(titleId: TitleId) = Credits(
        cast = cast.map { it.toCredit(titleId) },
        crew = crew.map { it.toCredit(titleId) }
    )
}

data class CreditsData(
    val adult: Boolean,
    val gender: Int? = null,
    val personId: Long,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profileUrl: String? = null,
    val profilePath: String? = null,
    val castId: Int? = null,
    val character: String? = null,
    val creditId: String,
    val order: Int? = null
) {
    fun toCredit(titleId: TitleId) = Credit(
        titleId = titleId,
        person = Person(
            id = PersonId.of(personId),
            name = name,
            profileUrl = profileUrl?.let { Url.of(it) }
        ),
        role = runCatching {
            when (CrewJob.valueOf(knownForDepartment)) {
                CrewJob.Directing -> Director()
                CrewJob.Acting -> Actor(characterName = character ?: "")
            }
        }.getOrNull()
    )
}