package tech.capitalcoding.pokedex.basic_feature.data.remote.api

import retrofit2.http.GET
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonListResponse

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(): List<PokemonListResponse>
}