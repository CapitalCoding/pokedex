package tech.capitalcoding.pokedex.basic_feature.presentation.model

class SearchPokemonList(
    val id: Int,
    val name: String,
    override val results: List<PokemonDisplayable>
) : PokemonList