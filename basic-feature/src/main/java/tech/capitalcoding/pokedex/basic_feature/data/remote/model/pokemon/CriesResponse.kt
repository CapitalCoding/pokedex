package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class CriesResponse(
    val latest: String,
    val legacy: String
)
