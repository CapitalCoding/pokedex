package tech.capitalcoding.pokedex.core.navigation

sealed class NavigationDestination(
    val route: String
) {
    data object Pokemons : NavigationDestination("pokemonsDestination")
    data object PokemonDetails : NavigationDestination("pokemonDetailsDestination")
    data object Search : NavigationDestination("searchDestination")
    data object Back : NavigationDestination("navigationBack")
}
