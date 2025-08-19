package com.semin.watlism.domain.value

@JvmInline
value class PersonId(val value: Long) {
    companion object {
        fun of(value: Long): PersonId {
            return PersonId(value)
        }
    }
}