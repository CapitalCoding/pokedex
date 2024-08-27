package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.type

import kotlinx.serialization.*
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.Slottable

@Serializable
data class TypeResponse(
    override val slot: Int,
    @SerialName("type")
    override val item: TypeXResponse
) : Slottable<TypeXResponse>

@Serializable
data class TypeXResponse(
    val name: String,
    val url: String
)
