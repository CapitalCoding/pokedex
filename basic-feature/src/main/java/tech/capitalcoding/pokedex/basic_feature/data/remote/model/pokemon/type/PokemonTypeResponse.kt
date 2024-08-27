package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.type

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeResponse(
    val name: String,
    val url: String
)
