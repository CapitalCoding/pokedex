package tech.capitalcoding.pokedex.basic_feature.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import tech.capitalcoding.pokedex.basic_feature.domain.model.Pokemon
import tech.capitalcoding.pokedex.basic_feature.domain.repository.PokemonRepository
import javax.inject.Inject

private const val RETRY_TIME_IN_MILLIS = 15_000L

class GetPokemonByIdOrNameUseCase @Inject constructor(
    private val repository: PokemonRepository
): UseCase<GetPokemonByIdOrNameParams, Pokemon>() {

    override fun execute(params: GetPokemonByIdOrNameParams) =
        repository.getPokemon(params.idOrName)
            .map { Result.success(it) }
            .retryWhen { cause, attempt ->
                if (cause is Exception) {
                    emit(Result.failure(cause))
                    delay(RETRY_TIME_IN_MILLIS)
                    true
                } else {
                    false
                }
            }.catch {
                emit(Result.failure(it))
            }
}

class GetPokemonByIdOrNameParams(val idOrName: String)
