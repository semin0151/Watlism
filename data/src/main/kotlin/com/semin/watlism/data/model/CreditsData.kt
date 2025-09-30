package com.semin.watlism.data.model

import com.semin.watlism.domain.model.Actor
import com.semin.watlism.domain.model.Credit
import com.semin.watlism.domain.model.CrewJob
import com.semin.watlism.domain.model.Director
import com.semin.watlism.domain.model.Person
import com.semin.watlism.domain.value.PersonId
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.Url

data class CreditsData(
    val adult: Boolean,
    val gender: Int? = null,
    val personId: Long,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profileUrl: String,
    val profilePath: String? = null,
    val castId: Int? = null,
    val character: String,
    val creditId: String,
    val order: Int
) {
    fun toCredit(titleId: TitleId) = Credit(
        titleId = titleId,
        person = Person(
            id = PersonId.of(personId),
            name = name,
            profileUrl = Url.of(profileUrl)
        ),
        role = runCatching {
            when (CrewJob.valueOf(knownForDepartment)) {
                CrewJob.Directing -> Director()
                CrewJob.Acting -> Actor(characterName = character)
            }
        }.getOrNull()
    )
}