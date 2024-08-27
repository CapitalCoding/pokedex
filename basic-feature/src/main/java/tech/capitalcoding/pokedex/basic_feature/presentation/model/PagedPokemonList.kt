package tech.capitalcoding.pokedex.basic_feature.presentation.model


class PagedPokemonList(
    override val results: List<PokemonDisplayable> = emptyList(),
    val count: Int,
    val next: String?,
    val previous: String?,
    val nextOffset: Int? = null,
    val previousOffset: Int? = null,
): PokemonList