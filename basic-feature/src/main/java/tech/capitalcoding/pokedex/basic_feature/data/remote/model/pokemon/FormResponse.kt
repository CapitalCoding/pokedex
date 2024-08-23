package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class FormResponse(
    val name: String,
    val url: String
)
