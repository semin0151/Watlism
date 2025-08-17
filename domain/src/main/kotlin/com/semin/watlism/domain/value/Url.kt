package com.semin.watlism.domain.value

@JvmInline
value class Url private constructor(val value: String) {
    companion object {
        fun of(value: String): Url {
            val scheme = runCatching { value.split("://").first() }.getOrNull()
            require(scheme == "http" || scheme == "https") { "Url must have http scheme."}
            return Url(value)
        }
    }
}