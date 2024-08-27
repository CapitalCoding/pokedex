package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon.type.TypeResponse

@Serializable
data class PokemonDetailResponse(
    val abilities: List<AbilityResponse>,
    val cries: CriesResponse,
    @SerialName("base_experience")
    val baseExperience: Int?,
    val forms: List<FormResponse>,
    val height: Int,
    val id: Int,
    @SerialName("is_default")
    val isDefault: Boolean,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String,
    val name: String,
    val order: Int,
    val sprites: SpritesResponse,
    val stats: List<StatsResponse>,
    val types: List<TypeResponse>,
    val weight: Int
)
