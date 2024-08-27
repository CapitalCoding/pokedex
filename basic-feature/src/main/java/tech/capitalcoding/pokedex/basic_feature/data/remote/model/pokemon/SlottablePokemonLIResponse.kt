package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SlottablePokemonLIResponse(
    @SerialName("pokemon")
    override val item: PokemonsListItemResponse,
    override val slot: Int,
) : Slottable<PokemonsListItemResponse>