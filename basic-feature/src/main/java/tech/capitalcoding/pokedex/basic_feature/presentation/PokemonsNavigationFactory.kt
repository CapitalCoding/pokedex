package tech.capitalcoding.pokedex.basic_feature.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import tech.capitalcoding.pokedex.basic_feature.presentation.composable.PokemonsRoute
import tech.capitalcoding.pokedex.core.navigation.NavigationDestination
import tech.capitalcoding.pokedex.core.navigation.NavigationFactory
import javax.inject.Inject

class PokemonsNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(NavigationDestination.Pokemons.route) {
            PokemonsRoute()
        }
    }

}
