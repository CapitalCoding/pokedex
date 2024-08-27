package tech.capitalcoding.pokedex.basic_feature.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonListResult
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonType
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonTypeListResult
import tech.capitalcoding.pokedex.basic_feature.domain.repository.PokemonRepository
import javax.inject.Inject

private const val RETRY_TIME_IN_MILLIS = 15_000L

class GetPokemonListByTypeUseCase @Inject constructor(
    private val repository: PokemonRepository
): UseCase<GetPokeListByTypeParams, PokemonTypeListResult<BasePokemon>>() {

    override fun execute(params: GetPokeListByTypeParams): Flow<Result<PokemonTypeListResult<BasePokemon>>> =
        repository.getPokemonListByType(params.type)
            .map { Result.success(it) }
            .retryWhen { cause, attempt ->
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


data class GetPokeListByTypeParams(val type: String)
