package tech.capitalcoding.pokedex.basic_feature.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.capitalcoding.pokedex.basic_feature.data.mapper.toDomainModel
import tech.capitalcoding.pokedex.basic_feature.data.remote.api.PokemonApi
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.Pokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonListResult
import tech.capitalcoding.pokedex.basic_feature.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<PokemonListResult<BasePokemon>> = flow {
        val response = pokemonApi.getPokemons(limit = limit, offset = offset)

        val basePokemonList = response.results.map { it.toDomainModel() }

        emit(
            PokemonListResult(
                count = response.count,
                next = response.next,
                previous = response.previous,
                items = basePokemonList,
                nextOffset = extractOffsetFromUrl(response.next),
                previousOffset = extractOffsetFromUrl(response.previous)
            )
        )
    }

    override suspend fun getPokemon(id: String): Flow<Pokemon> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonListByType(type: String): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonImage(id: String): Flow<String> {
        TODO("Not yet implemented")
    }

    private fun extractOffsetFromUrl(url: String?): Int? {
        return url?.let {
            val queryParams = it.split("?").last().split("&")
            val offsetParam = queryParams.find { param -> param.startsWith("offset=") }
            offsetParam?.split("=")?.get(1)?.toIntOrNull()
        }
    }
}

