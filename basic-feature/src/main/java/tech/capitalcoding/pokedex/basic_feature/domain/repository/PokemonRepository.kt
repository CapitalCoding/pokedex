package tech.capitalcoding.pokedex.basic_feature.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.capitalcoding.pokedex.basic_feature.domain.model.BasePokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.Pokemon
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonListResult
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonType
import tech.capitalcoding.pokedex.basic_feature.domain.model.PokemonTypeListResult
import tech.capitalcoding.pokedex.basic_feature.domain.model.SearchTypeListResult


interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<PokemonListResult<BasePokemon>>
    fun getPokemon(id: String): Flow<Pokemon>
    fun getPokemonListByType(type: String): Flow<PokemonTypeListResult<BasePokemon>>
    fun getPokemonTypeList(limit: Int, offset: Int): Flow<SearchTypeListResult<PokemonType>>
}