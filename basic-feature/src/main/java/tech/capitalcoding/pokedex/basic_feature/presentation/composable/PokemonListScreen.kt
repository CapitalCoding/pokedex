package tech.capitalcoding.pokedex.basic_feature.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
        ) {
            itemsIndexed(
                items = uiState.pokemons.items,
                key = { _, pokemon -> pokemon.id },
            ) { _, item ->
                PokemonItem(
                    pokemon = item,
                    onPokemonClick = { onIntent(PokemonsIntent.PokemonClicked(item.id.toInt())) },
                )
            }
            item {
                Spacer(modifier = Modifier.height(66.dp))
            }
        }

        uiState.pokemons.previousOffset?.let {
            OutlinedButton(
                onClick = { onIntent(PokemonsIntent.PreviousPage(it)) },
                enabled = uiState.pokemons.items.isNotEmpty() && !uiState.isLoading,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Previous")
            }
        }

        uiState.pokemons.nextOffset?.let {
            OutlinedButton(
                onClick = { onIntent(PokemonsIntent.LoadMorePokemons(it)) },
                enabled = uiState.pokemons.items.isNotEmpty() && !uiState.isLoading,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Next")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
            }
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