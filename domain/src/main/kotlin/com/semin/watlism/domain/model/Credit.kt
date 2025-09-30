package com.semin.watlism.domain.model

import com.semin.watlism.domain.value.TitleId

data class Credits(
    val cast: List<Credit>,
    val crew: List<Credit>
)

data class Credit(
    val titleId: TitleId,
    val person: Person,
    val role: Role?,
)
