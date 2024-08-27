package tech.capitalcoding.pokedex.basic_feature.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonListResult
import tech.capitalcoding.pokedex.basic_feature.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    ): UseCase<GetPokemonsParams, PokemonListResult<BasePokemon>>(){

    override fun execute(params: GetPokemonsParams): Flow<Result<PokemonListResult<BasePokemon>>> =
        pokemonRepository.getPokemonList(limit = params.limit, offset = params.offset)
            .map {
                Result.success(it)
            }
            .retryWhen {
                    cause, _ ->
                if (cause is Exception) {
                    emit(Result.failure(cause))
                    true
                } else {
                    false
                }
            }
            .catch {
                emit(Result.failure(it))
            }

}

data class GetPokemonsParams(val limit: Int, val offset: Int)

