package tech.capitalcoding.pokedex.basic_feature.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonListResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonDetailResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.type.PokemonTypeListItemResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.type.PokemonTypeListResponse
import tech.capitalcoding.pokedex.core.BuildConfig

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int =  0,
        @Query("limit") limit: Int = BuildConfig.POKEDEX_DEFAULT_PAGE_LIMIT
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonByIdOrName(@Path("id") id: String): PokemonDetailResponse

    @GET("type")
    suspend fun getPokemonTypes(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = BuildConfig.POKEDEX_DEFAULT_PAGE_LIMIT
    ): PokemonTypeListResponse

    @GET("type/{id}")
    suspend fun getPokemonTypeByName(@Path("id") name: String): PokemonTypeListItemResponse
}