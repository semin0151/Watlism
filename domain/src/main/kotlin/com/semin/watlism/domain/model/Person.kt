package com.semin.watlism.domain.model

import com.semin.watlism.domain.value.PersonId
import com.semin.watlism.domain.value.Url

data class Person(
    val id: PersonId,
    val name: String,
    val profileUrl: Url?,
)
