package com.semin.watlism.domain.usecase

import com.semin.watlism.domain.repository.MovieRepository
import com.semin.watlism.domain.repository.SeriesRepository
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.TitleType
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
) {
    operator fun invoke(
        titleId: TitleId,
        type: TitleType
    ) = when (type) {
        TitleType.movie -> movieRepository.getDetail(titleId)
        TitleType.tv -> seriesRepository.getDetail(titleId)
    }
}