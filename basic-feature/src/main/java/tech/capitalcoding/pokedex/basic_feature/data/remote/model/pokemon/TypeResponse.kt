package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class TypeResponse(
    val slot: Int,
    val type: TypeXResponse
)

@Serializable
data class TypeXResponse(
    val name: String,
    val url: String
)
