package com.semin.watlism.domain.value

@JvmInline
value class Rating private constructor(val value: Double) {
    companion object {
        fun of(v: Double): Rating {
            require(v in 0.0..10.0) { "Rating must be 0.0..10.0" }
            return Rating(v)
        }
    }
}