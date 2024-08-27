package tech.capitalcoding.pokedex.basic_feature.data.repository

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.capitalcoding.pokedex.basic_feature.data.mapper.toDomainModel
import tech.capitalcoding.pokedex.basic_feature.data.remote.api.PokemonApi
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.Pokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonListResult
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonType
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonTypeListResult
import tech.capitalcoding.pokedex.basic_feature.domain.model.SearchTypeListResult
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

    override fun getPokemon(id: String): Flow<Pokemon> {
        return flow {
            val response = pokemonApi.getPokemonByIdOrName(id)
            emit(response.toDomainModel())
        }
    }

    override fun getPokemonListByType(type: String): Flow<PokemonTypeListResult<BasePokemon>> = flow {
        val response = pokemonApi.getPokemonTypeByName(type.toLowerCase(Locale.current))

        val pokemonList = response.pokemon.map { it.item.toDomainModel() }

        emit(
            PokemonTypeListResult(
                id = response.id,
                name = response.name,
                results = pokemonList
            )
        )
    }

    override fun getPokemonTypeList(limit: Int, offset: Int): Flow<SearchTypeListResult<PokemonType>> = flow {
        val response = pokemonApi.getPokemonTypes(offset = offset, limit = limit)

        val pokemonTypeList = response.results.map { it.toDomainModel() }

        emit(
            SearchTypeListResult(
                count = response.count,
                next = response.next,
                previous = response.previous,
                items = pokemonTypeList,
                nextOffset = extractOffsetFromUrl(response.next),
                previousOffset = extractOffsetFromUrl(response.previous)
            )
        )
    }

    private fun extractOffsetFromUrl(url: String?): Int? {
        return url?.let {
            val queryParams = it.split("?").last().split("&")
            val offsetParam = queryParams.find { param -> param.startsWith("offset=") }
            offsetParam?.split("=")?.get(1)?.toIntOrNull()
        }
    }
}

