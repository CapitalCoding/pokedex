package tech.capitalcoding.pokedex.basic_feature.data.remote.model.pokemon

data class PokemonTypeListItemResponse(
    val name: String,
    val pokemon: List<PokemonsListItemResponse>,
)