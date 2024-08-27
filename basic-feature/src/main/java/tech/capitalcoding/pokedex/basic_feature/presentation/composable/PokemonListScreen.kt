package tech.capitalcoding.pokedex.basic_feature.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.Flow
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonListViewModel
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonsEvent
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonsIntent
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonsUiState
import tech.capitalcoding.pokedex.basic_feature.presentation.model.PagedPokemonList
import tech.capitalcoding.pokedex.core.utils.collectWithLifecycle

@Composable
fun PokemonsRoute(viewModel: PokemonListViewModel = hiltViewModel()) {
    HandleEvents(viewModel.getEvents())
    val uiState by viewModel.uiState.collectAsState()

    PokemonListScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent,
    )

}
@Composable
fun PokemonListScreen(
    uiState: PokemonsUiState,
    onIntent: (PokemonsIntent) -> Unit
) {
    PokemonListContent(
        items = uiState.pokemons.results,
        onPokemonItemClick = { pokemon -> onIntent(PokemonsIntent.PokemonClicked(pokemon.id.toInt())) },
        onPreviousPageClick = { (uiState.pokemons as? PagedPokemonList)?.let {
            uiState.pokemons.previousOffset?.let {
                onIntent(PokemonsIntent.PreviousPage(uiState.pokemons.previousOffset))
            }
        }  },
        onNextPageClick = { (uiState.pokemons as? PagedPokemonList)?.let {
            uiState.pokemons.nextOffset?.let {
                onIntent(PokemonsIntent.LoadMorePokemons(uiState.pokemons.nextOffset))
            }
        } },
        showPreviousPageBtn = (uiState.pokemons as? PagedPokemonList)?.let { uiState.pokemons.previousOffset != null } ?: false,
        showNextPageBtn = (uiState.pokemons as? PagedPokemonList)?.let { uiState.pokemons.nextOffset != null } ?: false,
    )
}


@Composable
private fun HandleEvents(events: Flow<PokemonsEvent>) {

    events.collectWithLifecycle {
        when (it) {
            is PokemonsEvent.openPokemonDetails -> {
                //TODO: open details
            }
        }
    }
}