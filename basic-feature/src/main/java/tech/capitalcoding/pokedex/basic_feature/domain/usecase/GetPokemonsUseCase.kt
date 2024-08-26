package tech.capitalcoding.pokedex.basic_feature.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.repository.PokemonRepository

fun interface GetPokemonsUseCase: () -> Flow<Result<List<BasePokemon>>>

fun getPokemons(pokemonRepository: PokemonRepository): Flow<Result<List<BasePokemon>>> = pokemonRepository
    .getPokemonList(limit = 20, offset = 0)
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