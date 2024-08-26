package tech.capitalcoding.pokedex.basic_feature.presentation

sealed class PokemonsEvent {
    data class openPokemonDetails(val id: String) : PokemonsEvent()
}
