package tech.capitalcoding.pokedex.basic_feature.presentation

sealed class PokemonsIntent {
    data object RefreshPokemons : PokemonsIntent()

    data class LoadMorePokemons(val offset: Int) : PokemonsIntent()

    data class PreviousPage(val offset: Int) : PokemonsIntent()

    data class PokemonClicked(val id: Int) : PokemonsIntent()


}
