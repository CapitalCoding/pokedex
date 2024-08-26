package tech.capitalcoding.pokedex.basic_feature.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.Flow
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonListViewModel
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonsEvent
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonsIntent
import tech.capitalcoding.pokedex.basic_feature.presentation.PokemonsUiState
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

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        itemsIndexed(
            items = uiState.pokemons,
            key = { _, pokemon -> pokemon.id },
        ) { index, item ->
            PokemonItem(
                pokemon = item,
                onPokemonClick = { onIntent(PokemonsIntent.PokemonClicked(item.id.toInt())) },
            )
        }
    }
}

@Composable
private fun HandleEvents(events: Flow<PokemonsEvent>) {
    val uriHandler = LocalUriHandler.current

    events.collectWithLifecycle {
        when (it) {
            is PokemonsEvent.openPokemonDetails -> {
                //TODO: open details
            }
        }
    }
}