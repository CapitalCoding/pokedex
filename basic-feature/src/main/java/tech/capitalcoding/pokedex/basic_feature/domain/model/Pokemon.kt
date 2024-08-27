package tech.capitalcoding.pokedex.basic_feature.domain.model

data class Pokemon(
    val id: String,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonType>,
    val abilities: List<String>,
    val stats: List<PokemonStat>,
    val sprites: PokemonSprites,
)
