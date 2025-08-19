package com.semin.watlism.domain.value

@JvmInline
value class TitleId private constructor(val value: Long) {
    companion object {
        fun of(value: Long): TitleId {
            return TitleId(value)
        }
    }
}