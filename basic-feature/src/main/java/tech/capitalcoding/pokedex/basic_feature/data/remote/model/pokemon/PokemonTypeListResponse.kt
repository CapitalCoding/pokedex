package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

import tech.capitalcoding.pokedex.basic_feature.data.remote.model.Pageable

data class PokemonTypeListResponse(
    override val count: Int,
    override val next: String,
    override val previous: String?,
    override val results: List<PokemonTypeResponse>
) : Pageable<PokemonTypeResponse>
