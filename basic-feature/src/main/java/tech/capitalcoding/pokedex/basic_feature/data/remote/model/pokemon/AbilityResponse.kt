package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class AbilityResponse(
    val ability: AbilityXResponse,
    val is_hidden: Boolean,
    val slot: Int
)

@Serializable
data class AbilityXResponse(
    val name: String,
    val url: String
)

