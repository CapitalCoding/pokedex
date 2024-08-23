package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsResponse(
    @SerialName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatXResponse
)

@Serializable
data class StatXResponse(
    val name: String,
    val url: String
)
