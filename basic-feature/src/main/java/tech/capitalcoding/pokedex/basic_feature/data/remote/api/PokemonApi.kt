package tech.capitalcoding.pokedex.basic_feature.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonListResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonDetailResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonTypeListResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonTypeResponse

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int =  0,
        @Query("limit") limit: Int = 20
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonDetailResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDetailResponse

    @GET("type")
    suspend fun getPokemonTypes(): PokemonTypeListResponse

    @GET("type/{id}")
    suspend fun getPokemonTypeByName(name: String): PokemonTypeResponse
}