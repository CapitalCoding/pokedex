package tech.capitalcoding.pokedex.basic_feature.data.remote.api

import retrofit2.http.GET
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonListResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonTypeListResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonTypeResponse

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonById(id: Int): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(name: String): PokemonResponse

    @GET("type")
    suspend fun getPokemonTypes(): PokemonTypeListResponse

    @GET("type/{id}")
    suspend fun getPokemonTypeByName(name: String): PokemonTypeResponse
}