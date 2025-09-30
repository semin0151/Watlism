package com.semin.watlism.domain.usecase

import com.semin.watlism.domain.model.MovieDetail
import com.semin.watlism.domain.model.SeriesDetail
import com.semin.watlism.domain.model.TitleDetail
import com.semin.watlism.domain.repository.MovieRepository
import com.semin.watlism.domain.repository.SeriesRepository
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.TitleType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
) {
    operator fun invoke(
        titleId: TitleId,
        type: TitleType
    ): Flow<TitleDetail> = getDetail(titleId, type).zip(getCredits(titleId, type)) { detail, credits ->
        when(detail) {
            is SeriesDetail -> detail.copy(credits = credits)
            is MovieDetail -> detail.copy(credits = credits)
        }
    }

    private fun getDetail(
        titleId: TitleId,
        type: TitleType
    ) = when (type) {
        TitleType.movie -> movieRepository.getDetail(titleId)
        TitleType.tv -> seriesRepository.getDetail(titleId)
    }

    private fun getCredits(
        titleId: TitleId,
        type: TitleType
    ) = when (type) {
        TitleType.movie -> movieRepository.getCredits(titleId)
        TitleType.tv -> seriesRepository.getCredits(titleId)
    }
}