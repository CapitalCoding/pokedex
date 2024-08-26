package tech.capitalcoding.pokedex.basic_feature.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.Pokemon


interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<List<BasePokemon>>
    suspend fun getPokemon(id: String): Flow<Pokemon>
    suspend fun getPokemonListByType(type: String): Flow<List<Pokemon>>
    suspend fun getPokemonImage(id: String): Flow<String>
}