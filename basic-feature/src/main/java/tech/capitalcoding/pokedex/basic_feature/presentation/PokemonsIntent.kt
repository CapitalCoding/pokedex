package tech.capitalcoding.pokedex.basic_feature.presentation

sealed class PokemonsIntent {
    data object RefreshPokemons : PokemonsIntent()

    data class PokemonClicked(val id: Int) : PokemonsIntent()


}
