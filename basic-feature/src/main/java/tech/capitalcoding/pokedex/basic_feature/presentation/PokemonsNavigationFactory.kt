package tech.capitalcoding.pokedex.basic_feature.presentation

import PokemonDetailRoute
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import tech.capitalcoding.pokedex.basic_feature.presentation.composable.PokemonsRoute
import tech.capitalcoding.pokedex.basic_feature.presentation.composable.SearchRoute
import tech.capitalcoding.pokedex.core.navigation.NavigationDestination
import tech.capitalcoding.pokedex.core.navigation.NavigationFactory
import javax.inject.Inject

class PokemonsNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(
            NavigationDestination.Pokemons.route + "?name={name}&type={type}&id={id}",
            arguments = listOf(navArgument("name") { type = NavType.StringType; nullable = true; defaultValue = null })
        ) {
                PokemonsRoute()
        }
        builder.composable(NavigationDestination.Search.route) {
            SearchRoute()
        }

        builder.composable(NavigationDestination.PokemonDetails.route + "/{pokemonId}") {
            PokemonDetailRoute()
        }

    }

}
