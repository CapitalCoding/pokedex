package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import kotlinx.serialization.SerialName

data class SlottablePokemonLIResponse(
    val name: String,
    @SerialName("pokemon")
    override val item: List<PokemonsListItemResponse>,
    override val slot: Int,
) : Slottable<List<PokemonsListItemResponse>>