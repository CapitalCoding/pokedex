package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.type

import kotlinx.serialization.Serializable
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.PokemonsListItemResponse
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.SlottablePokemonLIResponse

@Serializable
data class PokemonTypeListItemResponse(
    val id: Int,
    val name: String,
    val pokemon: List<SlottablePokemonLIResponse>,
)