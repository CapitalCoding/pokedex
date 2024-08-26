package tech.capitalcoding.pokedex.basic_feature.presentation.model

data class PokemonDetailsDisplayable(
    val id: String,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val abilities: List<String>,
    val stats: List<PokemonStatDisplayable>,
)
