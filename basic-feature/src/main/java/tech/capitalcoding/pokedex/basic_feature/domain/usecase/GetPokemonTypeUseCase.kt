package tech.capitalcoding.pokedex.basic_feature.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonType
import tech.capitalcoding.pokedex.basic_feature.domain.model.SearchTypeListResult
import tech.capitalcoding.pokedex.basic_feature.domain.repository.PokemonRepository
import tech.capitalcoding.pokedex.core.BuildConfig
import javax.inject.Inject

private const val RETRY_TIME_IN_MILLIS = 15_000L

class GetPokemonTypeUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : UseCase<GetPokemonTypeParams, SearchTypeListResult<PokemonType>>() {

    override fun execute(params: GetPokemonTypeParams): Flow<Result<SearchTypeListResult<PokemonType>>> =
        pokemonRepository.getPokemonTypeList(offset = params.offset, limit = params.limit)
            .map {
                Result.success(it)
            }
            .retryWhen {
                cause, _ ->
                if (cause is Exception) {
                    emit(Result.failure(cause))
                    delay(RETRY_TIME_IN_MILLIS)
                    true
                } else {
                    false
                }
            }
            .catch {
                emit(Result.failure(it))
            }

}

data class GetPokemonTypeParams(val offset: Int, val limit: Int = BuildConfig.POKEDEX_DEFAULT_PAGE_LIMIT){
    companion object {
        fun default() = GetPokemonTypeParams(offset = 0, limit = 20)
    }
}
