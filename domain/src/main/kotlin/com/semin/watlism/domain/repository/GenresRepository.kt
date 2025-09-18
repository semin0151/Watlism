package com.semin.watlism.domain.repository

import com.semin.watlism.domain.model.Genre

interface GenresRepository {
    suspend fun getGenreIdByName(name: String): Genre?
}