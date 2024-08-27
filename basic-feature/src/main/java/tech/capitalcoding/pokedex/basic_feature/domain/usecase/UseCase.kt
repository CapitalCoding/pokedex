package tech.capitalcoding.pokedex.basic_feature.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class UseCase<in P, R> {
    abstract fun execute(params: P): Flow<Result<R>>
}