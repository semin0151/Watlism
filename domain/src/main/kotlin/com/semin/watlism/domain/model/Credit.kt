package com.semin.watlism.domain.model

import com.semin.watlism.domain.value.PersonId
import com.semin.watlism.domain.value.TitleId

data class Credit(
    val titleId: TitleId,
    val personId: PersonId,
    val role: Role,
)
